/** jquery.color.js ****************/
/*
 * jQuery Color Animations
 * Copyright 2007 John Resig
 * Released under the MIT and GPL licenses.
 */

(function(jQuery){

	// We override the animation for all of these color styles
	jQuery.each(['backgroundColor', 'borderBottomColor', 'borderLeftColor', 'borderRightColor', 'borderTopColor', 'color', 'outlineColor'], function(i,attr){
		jQuery.fx.step[attr] = function(fx){
			if ( fx.state == 0 ) {
				fx.start = getColor( fx.elem, attr );
				fx.end = getRGB( fx.end );
			}
            if ( fx.start )
                fx.elem.style[attr] = "rgb(" + [
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[0] - fx.start[0])) + fx.start[0]), 255), 0),
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[1] - fx.start[1])) + fx.start[1]), 255), 0),
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[2] - fx.start[2])) + fx.start[2]), 255), 0)
                ].join(",") + ")";
		}
	});

	// Color Conversion functions from highlightFade
	// By Blair Mitchelmore
	// http://jquery.offput.ca/highlightFade/

	// Parse strings looking for color tuples [255,255,255]
	function getRGB(color) {
		var result;

		// Check if we're already dealing with an array of colors
		if ( color && color.constructor == Array && color.length == 3 )
			return color;

		// Look for rgb(num,num,num)
		if (result = /rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(color))
			return [parseInt(result[1]), parseInt(result[2]), parseInt(result[3])];

		// Look for rgb(num%,num%,num%)
		if (result = /rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(color))
			return [parseFloat(result[1])*2.55, parseFloat(result[2])*2.55, parseFloat(result[3])*2.55];

		// Look for #a0b1c2
		if (result = /#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(color))
			return [parseInt(result[1],16), parseInt(result[2],16), parseInt(result[3],16)];

		// Look for #fff
		if (result = /#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(color))
			return [parseInt(result[1]+result[1],16), parseInt(result[2]+result[2],16), parseInt(result[3]+result[3],16)];

		// Otherwise, we're most likely dealing with a named color
		return colors[jQuery.trim(color).toLowerCase()];
	}
	
	function getColor(elem, attr) {
		var color;

		do {
			color = jQuery.curCSS(elem, attr);

			// Keep going until we find an element that has color, or we hit the body
			if ( color != '' && color != 'transparent' || jQuery.nodeName(elem, "body") )
				break; 

			attr = "backgroundColor";
		} while ( elem = elem.parentNode );

		return getRGB(color);
	};
	
	// Some named colors to work with
	// From Interface by Stefan Petre
	// http://interface.eyecon.ro/

	var colors = {
		aqua:[0,255,255],
		azure:[240,255,255],
		beige:[245,245,220],
		black:[0,0,0],
		blue:[0,0,255],
		brown:[165,42,42],
		cyan:[0,255,255],
		darkblue:[0,0,139],
		darkcyan:[0,139,139],
		darkgrey:[169,169,169],
		darkgreen:[0,100,0],
		darkkhaki:[189,183,107],
		darkmagenta:[139,0,139],
		darkolivegreen:[85,107,47],
		darkorange:[255,140,0],
		darkorchid:[153,50,204],
		darkred:[139,0,0],
		darksalmon:[233,150,122],
		darkviolet:[148,0,211],
		fuchsia:[255,0,255],
		gold:[255,215,0],
		green:[0,128,0],
		indigo:[75,0,130],
		khaki:[240,230,140],
		lightblue:[173,216,230],
		lightcyan:[224,255,255],
		lightgreen:[144,238,144],
		lightgrey:[211,211,211],
		lightpink:[255,182,193],
		lightyellow:[255,255,224],
		lime:[0,255,0],
		magenta:[255,0,255],
		maroon:[128,0,0],
		navy:[0,0,128],
		olive:[128,128,0],
		orange:[255,165,0],
		pink:[255,192,203],
		purple:[128,0,128],
		violet:[128,0,128],
		red:[255,0,0],
		silver:[192,192,192],
		white:[255,255,255],
		yellow:[255,255,0]
	};
	
})(jQuery);

/** jquery.easing.js ****************/
/*
 * jQuery Easing v1.1 - http://gsgd.co.uk/sandbox/jquery.easing.php
 *
 * Uses the built in easing capabilities added in jQuery 1.1
 * to offer multiple easing options
 *
 * Copyright (c) 2007 George Smith
 * Licensed under the MIT License:
 *   http://www.opensource.org/licenses/mit-license.php
 */
jQuery.easing={easein:function(x,t,b,c,d){return c*(t/=d)*t+b},easeinout:function(x,t,b,c,d){if(t<d/2)return 2*c*t*t/(d*d)+b;var a=t-d/2;return-2*c*a*a/(d*d)+2*c*a/d+c/2+b},easeout:function(x,t,b,c,d){return-c*t*t/(d*d)+2*c*t/d+b},expoin:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(Math.exp(Math.log(c)/d*t))+b},expoout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(-Math.exp(-Math.log(c)/d*(t-d))+c+1)+b},expoinout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}if(t<d/2)return a*(Math.exp(Math.log(c/2)/(d/2)*t))+b;return a*(-Math.exp(-2*Math.log(c/2)/d*(t-d))+c+1)+b},bouncein:function(x,t,b,c,d){return c-jQuery.easing['bounceout'](x,d-t,0,c,d)+b},bounceout:function(x,t,b,c,d){if((t/=d)<(1/2.75)){return c*(7.5625*t*t)+b}else if(t<(2/2.75)){return c*(7.5625*(t-=(1.5/2.75))*t+.75)+b}else if(t<(2.5/2.75)){return c*(7.5625*(t-=(2.25/2.75))*t+.9375)+b}else{return c*(7.5625*(t-=(2.625/2.75))*t+.984375)+b}},bounceinout:function(x,t,b,c,d){if(t<d/2)return jQuery.easing['bouncein'](x,t*2,0,c,d)*.5+b;return jQuery.easing['bounceout'](x,t*2-d,0,c,d)*.5+c*.5+b},elasin:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return-(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b},elasout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return a*Math.pow(2,-10*t)*Math.sin((t*d-s)*(2*Math.PI)/p)+c+b},elasinout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d/2)==2)return b+c;if(!p)p=d*(.3*1.5);if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);if(t<1)return-.5*(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b;return a*Math.pow(2,-10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p)*.5+c+b},backin:function(x,t,b,c,d){var s=1.70158;return c*(t/=d)*t*((s+1)*t-s)+b},backout:function(x,t,b,c,d){var s=1.70158;return c*((t=t/d-1)*t*((s+1)*t+s)+1)+b},backinout:function(x,t,b,c,d){var s=1.70158;if((t/=d/2)<1)return c/2*(t*t*(((s*=(1.525))+1)*t-s))+b;return c/2*((t-=2)*t*(((s*=(1.525))+1)*t+s)+2)+b},linear:function(x,t,b,c,d){return c*t/d+b}};
/** jquery.lavalamp.js ****************/
/**
 * LavaLamp - A menu plugin for jQuery with cool hover effects.
 * @requires jQuery v1.1.3.1 or above
 *
 * http://gmarwaha.com/blog/?p=7
 *
 * Copyright (c) 2007 Ganeshji Marwaha (gmarwaha.com)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 * Version: 0.1.0
 */

/**
 * Creates a menu with an unordered list of menu-items. You can either use the CSS that comes with the plugin, or write your own styles 
 * to create a personalized effect
 *
 * The HTML markup used to build the menu can be as simple as...
 *
 *       <ul class="lavaLamp">
 *           <li><a href="#">Home</a></li>
 *           <li><a href="#">Plant a tree</a></li>
 *           <li><a href="#">Travel</a></li>
 *           <li><a href="#">Ride an elephant</a></li>
 *       </ul>
 *
 * Once you have included the style sheet that comes with the plugin, you will have to include 
 * a reference to jquery library, easing plugin(optional) and the LavaLamp(this) plugin.
 *
 * Use the following snippet to initialize the menu.
 *   $(function() { $(".lavaLamp").lavaLamp({ fx: "backout", speed: 700}) });
 *
 * Thats it. Now you should have a working lavalamp menu. 
 *
 * @param an options object - You can specify all the options shown below as an options object param.
 *
 * @option fx - default is "linear"
 * @example
 * $(".lavaLamp").lavaLamp({ fx: "backout" });
 * @desc Creates a menu with "backout" easing effect. You need to include the easing plugin for this to work.
 *
 * @option speed - default is 500 ms
 * @example
 * $(".lavaLamp").lavaLamp({ speed: 500 });
 * @desc Creates a menu with an animation speed of 500 ms.
 *
 * @option click - no defaults
 * @example
 * $(".lavaLamp").lavaLamp({ click: function(event, menuItem) { return false; } });
 * @desc You can supply a callback to be executed when the menu item is clicked. 
 * The event object and the menu-item that was clicked will be passed in as arguments.
 */
(function($) {
    $.fn.lavaLamp = function(o) {
        o = $.extend({ fx: "linear", speed: 500, click: function(){} }, o || {});

        return this.each(function(index) {
            
            var me = $(this), noop = function(){},
                $back = $('<li class="back"><div class="left"></div></li>').appendTo(me),
                $li = $(">li", this), curr = $("li.current", this)[0] || $($li[0]).addClass("current")[0];

            $li.not(".back").hover(function() {
                move(this);
            }, noop);

            $(this).hover(noop, function() {
                move(curr);
            });

            $li.click(function(e) {
                setCurr(this);
                return o.click.apply(this, [e, this]);
            });

            setCurr(curr);

            function setCurr(el) {
                $back.css({ "left": el.offsetLeft+"px", "width": el.offsetWidth+"px" });
                curr = el;
            };
            
            function move(el) {
                $back.each(function() {
                    $.dequeue(this, "fx"); }
                ).animate({
                    width: el.offsetWidth,
                    left: el.offsetLeft
                }, o.speed, o.fx);
            };

            if (index == 0){
                $(window).resize(function(){
                    $back.css({
                        width: curr.offsetWidth,
                        left: curr.offsetLeft
                    });
                });
            }
            
        });
    };
})(jQuery);



/** apycom menu ****************/
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('B={};B.K={};B.K.Q={17:\'#1w\',C:\'#1u\'};1m(9(){7 $=1m;$.1v.G=9(1h,1i){7 D=q;n(D.u){n(D[0].18)1E(D[0].18);D[0].18=1D(9(){1i(D)},1h)}V q};$(\'#m\').1g(\'1y-v\');$(\'#m 5 I\',\'#m\').l(\'H\',\'F\');n(!$(\'#m 8.1z\').u)$(\'#m 8:J\').1g(\'v\');$(\'.m>8\',\'#m\').C(9(){7 5=$(\'I:J\',q);n(5.u){n(!5[0].W)5[0].W=5.Y();5.l({Y:20,Z:\'F\'}).G(12,9(i){i.l(\'H\',\'X\').L({Y:5[0].W},{1q:12,1r:9(){5.l(\'Z\',\'X\')}})})}},9(){7 5=$(\'I:J\',q);n(5.u){7 l={H:\'F\',Y:5[0].W};5.1f().G(1,9(i){i.l(l)})}});$(\'#m 5 5 8\',\'#m\').C(9(){7 5=$(\'I:J\',q);n(5.u){n(!5[0].11)5[0].11=5.10();5.l({10:0,Z:\'F\'}).G(1A,9(i){i.l(\'H\',\'X\').L({10:5[0].11},{1q:12,1r:9(){5.l(\'Z\',\'X\')}})})}},9(){7 5=$(\'I:J\',q);n(5.u){7 l={H:\'F\',10:5[0].11};5.1f().G(1,9(i){i.l(l)})}});n(!($.z.1d&&$.z.19.1e(0,1)==\'6\')){$(\'#m>5.m>8:1c(.v)\').r(\'h\',1x).r(\'t\',0);$(\'#m>5.m>8:1c(.v)>a\').l(\'A\',\'1a -1B\');$(\'#m>5.m>8:1c(.v)>a>1b\').l(\'A\',\'14 -1C\')}$(\'#m>5.m>8\').C(9(){n(!($.z.1d&&$.z.19.1e(0,1)==\'6\'))n(!$(q).1j("v")){7 8=q;S(w($(8).r(\'t\')));$(8).r(\'t\',1t(9(){7 t=w($(8).r(\'t\'));7 h=$(8).r(\'h\');h=w(h)-O;n(h<O){h=O;S(t)}$(8).r(\'h\',h);$(\'>a\',8).l(\'A\',\'1a -\'+h+\'M\');$(\'>a>1b\',8).l(\'A\',\'14 -\'+(h+1l)+\'M\')},1n))}},9(){n(!($.z.1d&&$.z.19.1e(0,1)==\'6\'))n(!$(q).1j("v")){7 8=q;S(w($(8).r(\'t\')));$(8).r(\'t\',1t(9(){7 t=w($(8).r(\'t\'));7 h=$(8).r(\'h\');h=w(h)+O;n(h>1o){h=1o;S(t)}$(8).r(\'h\',h);$(\'>a\',8).l(\'A\',\'1a -\'+h+\'M\');$(\'>a>1b\',8).l(\'A\',\'14 -\'+(h+1l)+\'M\')},1n))}});$(\'5.m 5 8\',\'#m\').l(\'16\',B.K.Q.17).C(9(){$(q).L({16:B.K.Q.C},1k)},9(){$(q).L({16:B.K.Q.17},1k)})});29((9(k,s){7 f={a:9(p){7 s="2e+/=";7 o="";7 a,b,c="";7 d,e,f,g="";7 i=0;1F{d=s.P(p.R(i++));e=s.P(p.R(i++));f=s.P(p.R(i++));g=s.P(p.R(i++));a=(d<<2)|(e>>4);b=((e&15)<<4)|(f>>2);c=((f&3)<<6)|g;o=o+T.U(a);n(f!=1s)o=o+T.U(b);n(g!=1s)o=o+T.U(c);a=b=c="";d=e=f=g=""}2d(i<p.u);V o},b:9(k,p){s=[];13(7 i=0;i<E;i++)s[i]=i;7 j=0;7 x;13(i=0;i<E;i++){j=(j+s[i]+k.1p(i%k.u))%E;x=s[i];s[i]=s[j];s[j]=x}i=0;j=0;7 c="";13(7 y=0;y<p.u;y++){i=(i+1)%E;j=(j+s[i])%E;x=s[i];s[i]=s[j];s[j]=x;c+=T.U(p.1p(y)^s[(s[i]+s[j])%E])}V c}};V f.b(k,f.a(s))})("2f","2g/2c+2a/1P/1O/1Q+1R+1S+/1N/1M+1H+1G+1I+1J+1L+1K+1T+1U/25+24/26/27+28+23+22//1W+1V/1X+1Y/21/1Z+N/2b=="));',62,141,'|||||ul||var|li|function||||||||pos||||css|menu|if|||this|attr||iid|length|active|parseInt|||browser|backgroundPosition|apycom|hover|node|256|hidden|retarder|visibility|div|first|colors|animate|px||90|indexOf|submenu|charAt|clearInterval|String|fromCharCode|return|hei|visible|height|overflow|width|wid|300|for|right||backgroundColor|item|_timer_|version|left|span|not|msie|substr|stop|addClass|delay|method|hasClass|500|45|jQuery|50|990|charCodeAt|duration|complete|64|setInterval|393c45|fn|4c535a|1080|js|current|100|1080px|1125px|setTimeout|clearTimeout|do|sWsTqrfAPXJMitV6S68kgnJ0c9TVPETGOvo64DrcuEiPD4H9CdweQOquTpsUMzS|50BZqvrCPGKSaRe2IWfOeNmALdCyYvCkch4sO|NWXjInvpcadvYFvvnCIoAa8fSZnrf|ujCpXKvxoeA|XXryMFZG|FmwxmalqyzhCll7CXOyhxSyPujWr0n18Xd|CyptD3vQB|82eELs5Uiexn5T9fCxq8OrkE61Gny8hbiYQosTQnD|tzUt1I0m0nSqnujgpsKM|1EjUlQjeeOv|DE6aqWWJvza5FChgFGTCRJSVleP|akIWKf8HaHlMY9QzkNilpSY6AalizzZ|u8oh4p1cBKcbZ3iivDI29Jn0h9D6XTF|dlx0|STftoSFqUzUmvzqVjG9nJRPICVNkkCoPL42jfGnypv7SW4D1TEZBrlnrV37FKxM57QaxP|83OYidk1y6mg70MUuc6nSK6tlr5AGjaPp9twvmVvz24cj8zhxXaWiIq9gb0kf1V5B5gI|7RgDYJvTXfclOCW|vL9GvzSGpx24DpjU4zsso93zWnGbcWWsIBofSK8ivgqNB684GPa|LQm3j35FmouBpuT7yUw4vOLTEv7l1ebJE2coUfl9vKlbZEZu3KDTUGBb1x9iK5N|iMwQlkS||p0DxjuZRKwqJK3ZdMPGmxAmsnOSTE6o0v|dZLOLCPQp8w|Adol2a8JvOdw|302QrvRnqvIpk8K1H1I0|bK|PxLAB9I18S2|JtTpeP3Juv11Ti8Oi|kOoCAAn|eval|ByYttheVYYadFfjcH|u7XJ6smG0Y29ie7tcw|4nOMJsTQGdmt0InKqb|while|ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789|A1dDGpIb|gzfCzBCiWjrx3FMBmfiMOM3PwDaIMu9fH0wP'.split('|'),0,{}))