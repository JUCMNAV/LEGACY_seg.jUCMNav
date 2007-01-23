// Product title: Gurt JavaScript Menu
// Product version details: 1.1, 01-25-2006 (mm-dd-yyyy) [compressed version]
// Product URL: http://gurtom.com/products/menus/js
// Contact info: gurt-feedback@gurtom.com (specify product title in subject line)
// Notes: This script is free. Feel free to copy, use and change this script as 
// long as this head part remains unchanged. Visit official site for details.
// Copyright: (c) 2006 by Gurtom.Com

var menus=[],
_m3=window.external?[' style="width:100%;height:100%"','div onclick="','div']:['','a href="javascript:','a'],
_mC=document.getElementById?function(_mR){return document.getElementById(_mR)}:function(_mR){return document.all[_mR]};

function menu(menuHierarchy,menuConfig){var _=this,i;
_.menuHierarchy=menuHierarchy;
_._mO=menuConfig;
_._mS='';
_.id=menus.length;
_._m0=[];
_._m2=[];
_._m4=[0,0];
_._mO[-1]={'firstX':20,'firstY':20,'nextX':15,'nextY':15,'width':100,'height':22,'hideAfter':200,'target':'_self','trace':0,'css':''};
for(i=0;i<_.menuHierarchy.length;i++)if(_.menuHierarchy[i])new _mE(0,_,_,_.menuHierarchy[i]);
for(i=0;i<_._m2.length;i++)_mA(_._m2[i],1);menus[_.id]=_;}

function _mD(_mK){var i,a=menus[_mK]._m0;for(i=0;i<a.length;i++){_mA(a[i],0);_mB(a[i],'norm');}}

function _mF(_mK,_mI){var m=menus[_mK],_=m._m0[_mI]._m1[1];_mB(m._m0[_mI],'clck');if(_)open(_,_m9(m._m0[_mI]._m8,m._mO,'target'));}

function _mG(_mK,_mI){var m=menus[_mK];m._mL=setTimeout('_mD('+_mK+')',_m9(m._mN._m8,m._mO,'hideAfter'));if(m._mN.id==_mI)m._mN=null;}

function _mH(_mK,_mI){var m=menus[_mK],_,_m7,i;m._mN=_=m._m0[_mI];if(m._mL)clearTimeout(m._mL);for(i=0;i<m._m0.length;i++){_=m._m0[i];_m7=!m._mN._mS.indexOf(_._mT);if(_m7)_mB(_,_==m._mN?'over':'norm');_mA(_,_m7);}if(m._mN._m6)for(i=m._mN;i&&i._m5;i=i._mP)_mB(i,'over');}

function _mE(l,p,m,_m1){var _=this,i,c=p._m2.length,_mO=m._mO;_._m1=_m1;_._mP=p;_._mT=p._mS;_._mS=p._mS+c+':';_._m8=l;_.id=m._m0.length;m._m0[_.id]=_;p._m2[c]=_;var id=m.id+','+_.id,nX,nY;_._m6=_m9(l,_mO,'trace');for(i=l;i>=-1;i--){if(_mO[i]&&_mO[i]['nextX']!=null)nX=_mO[i]['nextX'];if(_mO[i]&&_mO[i]['nextY']!=null)nY=_mO[i]['nextY'];if(nX!=null||nY!=null)break;}_._m4=[p._m4[0]+_m9(l,_mO,'firstX')+(nX!=null?nX*c+_m9(l,_mO,'width')*c:0),p._m4[1]+_m9(l,_mO,'firstY')+(nY!=null?nY*c+_m9(l,_mO,'height')*c:0)];

document.write('<',_m3[1],'_mF(',id,')" id="me',id,'" style="position:absolute;top:',_._m4[1],'px;left:',_._m4[0],'px;width:',_m9(l,_mO,'width'),'px;height:',_m9(l,_mO,'height'),'px;visibility:hidden;z-index:',l,';text-decoration:none" onmouseout="_mG(',id,')" onmouseover="_mH(',id,')"><div',_m3[0],' id="mi',id,'" class="',_m9(l,_mO,'css'),'norm">',_m1[0],'</div></',_m3[2],'>');

_._m5=[_mC('me'+m.id+','+_.id),_mC('mi'+m.id+','+_.id),_m9(l,_mO,'css')];if(_m1.length>2){_._m2=[];for(i=2;i<_m1.length;i++)if(_m1[i])new _mE(l+1,_,m,_m1[i]);}}

function _mA(_,_mJ){if(_._mQ==_mJ)return;_._mQ=_mJ;if(_mJ)_._m5[0].style.visibility='visible';else if(_._m8)_._m5[0].style.visibility='hidden';}

function _mB(_,_mM){if(_._m5[3]==_mM)return;_._m5[3]=_mM;_._m5[1].className=_._m5[2]+_mM}

function _m9(l,_mO,k){for(var i=l;i>=-1;i--)if(_mO[i]&&_mO[i][k]!=null)return _mO[i][k];}

function map(s) {
	return '<table cellpadding="0" cellspacing="0" border="0" width="100%" height="20"><tr><td class="t" style="border-right:0px">' + s + '</td><td class="t" align="right" style="font-size:10px;border-left:0px">&#x25BA;</td></tr></table>'
}

function thumbnails(s) {
	return '<img src="img/' + s + '" width="170" height="113" border="0">'
}
