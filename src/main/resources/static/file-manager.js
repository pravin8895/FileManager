window.onload = function(){
	var components ={};
	components.radioDeleteButton = document.getElementById('radioDelete');
	components.radioCopyButton =document.getElementById('radioCopy');
	components.radioDownloadButton =document.getElementById('radioDownload');
	components.deleteButton = document.getElementById('deleteButton');
	components.copyButton = document.getElementById('copyButton');
	components.downloadButton = document.getElementById('downloadButton');
	components.statusText = document.getElementById('status');
	components.destinationText = document.getElementById('destination');
	components.destinationLabel = document.getElementById('destination_label');
	components.sourceText = document.getElementById('source');
	
	
	components.radioDeleteButton.onclick = function(){
		setDestinationHidden(true);
		resetButtonsAndStatus();
		toggleButtonVisibility(components.deleteButton);
	}
	
	components.radioCopyButton.onclick = function(){
		setDestinationHidden(false);
		resetButtonsAndStatus();
		toggleButtonVisibility(components.copyButton);
		
	}
	components.radioDownloadButton.onclick = function(){
		setDestinationHidden(true);
		resetButtonsAndStatus();
		toggleButtonVisibility(components.downloadButton);
	}
	
	components.deleteButton.onclick = function(){
		updateStatus("Please Wait While processing...");
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  updateStatus("DELETED SUCCESSFULLY");
			  }else if(this.status == 500){
				  updateStatus("ERROR OCCURED");
			  }
			};
			xhttp.open("GET", "/delete?sourceFile="+components.sourceText.value, true);
		  xhttp.send();
	};
	
	components.downloadButton.onclick = function(){
		updateStatus("Please Wait While processing...");
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  updateStatus("DOWNLOADED SUCCESSFULLY - " +
				    		this.responseText);
			  }else if(this.status == 500){
				  updateStatus("ERROR OCCURED");
			  }
			};
			xhttp.open("GET", "/download?downloadUrl="+components.sourceText.value, true);
		  xhttp.send();
	}
	
	components.copyButton.onclick = function(){
		updateStatus("Please Wait While processing...");
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
				  updateStatus("COPIED SUCCESSFULLY");
			  }else if(this.status == 500){
				  updateStatus("ERROR OCCURED");
			  }
			};
			xhttp.open("GET", "/copy?sourceFile="+
					components.sourceText.value+"&destinationFolder="+
					components.destinationText.value, true);
		  xhttp.send();
	}
	
	
	function resetButtonsAndStatus() {
		components.deleteButton.hidden = true;
		components.copyButton.hidden = true;
		components.downloadButton.hidden = true;
		components.statusText.innerText = ""
	}
	
	function toggleButtonVisibility(selectedButton) {
		selectedButton.hidden = false;
	}
	function updateStatus(status) {
		components.statusText.innerText = status;
	}
	function setDestinationHidden(isHidden) {
		components.destinationLabel.hidden = isHidden;
		components.destinationText.hidden = isHidden;
	}
	
}
