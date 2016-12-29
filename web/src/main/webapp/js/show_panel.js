/**
 * Created by oxothuk1401 on 29.12.2016.
 */
function showPanel() {
    $("#panel").show(1000, function () {
        $("#hide").show();
        $("#show").hide();
    });
}
function hidePanel() {
    $("#panel").hide(1000, function () {
        $("#hide").hide();
        $("#show").show();
    });
}
$(document).ready( function () {
    $("#hide").bind("click", hidePanel);
    $("#show").bind("click", showPanel);
});