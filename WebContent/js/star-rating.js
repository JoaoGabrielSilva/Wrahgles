function showvoto(item) {var nota = document.getElementById(item + 'val').value;if(!nota) { nota=0;}; votoover(nota/2,item);}
function setvoto(valor,item) {	document.getElementById(item + 'val').value=valor;}
function votoover(valor, item) { var itemx=item+ '0';
if(valor==0) {document.getElementById(item + '00').className=item +'_0_on';} else {document.getElementById(item + '00').className=item + '_0_off';}
var txt = document.getElementById('txt' + item).value.split(';');
document.getElementById(item + 'txt').innerHTML = txt[valor];
for (i = 1; i<=valor; i++)
		document.getElementById(itemx + i).className = item + '_on';	
	for (; i<=5; i++)
		document.getElementById(itemx + i).className = item + '_off';
}