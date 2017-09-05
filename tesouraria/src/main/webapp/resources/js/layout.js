if(window.addEventListener) {
    window.addEventListener("load", function() { centraliza(); }, false);
} else {
    if(window.attachEvent) {
    window.attachEvent("onload", function() { centraliza(); });
    }
}
function centraliza(){
    largura=document.documentElement.clientWidth;
    margem = (largura/2)-400;
    document.getElementById('conteudoMeio').style.marginLeft=margem+"px";
    setTimeout("centraliza()", 1);
}