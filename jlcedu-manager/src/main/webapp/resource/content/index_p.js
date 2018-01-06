$(function () {
    $("#focus").hover(function () {
    	$("#focus-prev, #focus-next").show();
    }, function () {
    	$("#focus-prev, #focus-next").hide();
    });
    $("#focus").slide({
        mainCell: "#focus-bar-box ul",
        targetCell: "#focus-title a",
        titCell: "#focus-num a",
        prevCell: "#focus-prev",
        nextCell: "#focus-next",
        effect: "fold",
        easing: "easeInOutCirc",
        autoPlay: true,
        delayTime: 1000
    });
    jQuery(".slideBox").slide({
    	mainCell:".bd ul",
    	effect:"left",
    	autoPlay:true
    });
})
