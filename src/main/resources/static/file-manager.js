window.onload = function(){
	
	document.getElementById('radioDelete').onclick = function(){
		setDestinationHidden(true);
		resetButtonsAndStatus();
		toggleButtonVisibility('deleteButton');
	}
	
	document.getElementById('radioCopy').onclick = function(){
		setDestinationHidden(false);
		resetButtonsAndStatus();
		toggleButtonVisibility('copyButton');
		
	}
	document.getElementById('radioDownload').onclick = function(){
		setDestinationHidden(true);
		resetButtonsAndStatus();
		toggleButtonVisibility('downloadButton');
	}
	
	document.getElementById('deleteButton').onclick = function(){
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    document.getElementById("status").innerText = "DELETED SUCCESSFULLY"
			  }
			};
			xhttp.open("GET", "/delete?sourceFile="+document.getElementById("source").value, true);
		  xhttp.send();
	}
	
	document.getElementById('downloadButton').onclick = function(){
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  this.responseText
			    document.getElementById("status").innerText = "DOWNLOADED SUCCESSFULLY - " +
			    		this.responseText;
			    
			  }
			};
			xhttp.open("GET", "/download?downloadUrl="+document.getElementById("source").value, true);
		  xhttp.send();
	}
	
	document.getElementById('copyButton').onclick = function(){
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			    document.getElementById("status").innerText = "COPIED SUCCESSFULLY"
			  }
			};
			xhttp.open("GET", "/copy?sourceFile="+document.getElementById("source").value+"&destinationFolder="+document.getElementById("destination").value, true);
		  xhttp.send();
	}
	
	
	function resetButtonsAndStatus() {
		document.getElementById('deleteButton').hidden = true;
		document.getElementById('copyButton').hidden = true;
		document.getElementById('downloadButton').hidden = true;
		document.getElementById("status").innerText = ""
	}
	
	function toggleButtonVisibility(buttonId) {
		document.getElementById(buttonId).hidden = false;
	}
	
	function setDestinationHidden(isHidden) {
		document.getElementById('destination_label').hidden = isHidden;
		document.getElementById('destination').hidden = isHidden;
	}
	
}
