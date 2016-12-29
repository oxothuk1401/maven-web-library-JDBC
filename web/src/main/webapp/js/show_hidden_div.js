document.("#show_div").onclick = function() {
	var hiddenDiv = document.("#hidden_div");
	if (hiddenDiv.style.display == "none") {
		hiddenDiv.style.display = "block"
	} else {
		hiddenDiv.style.display = "none";
	}
};