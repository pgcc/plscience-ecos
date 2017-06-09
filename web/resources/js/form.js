/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global PF */

function start() {
    PF('statusDialog').show();
}
 
function stop() {
    PF('statusDialog').hide();
}

function clearBox(elementID)
{
    var div = document.getElementById(elementID);
    while(div.firstChild){
        div.removeChild(div.firstChild);
    }    
}

function clearFirstChild(elementID)
{
    var div = document.getElementById(elementID);
    div.empty();
}