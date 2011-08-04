/**
<p>
    It is {@link com.fraudpointer.api.IClient IClient} that can tell you how, you can start working with Fraudpointer.
</p>

<p>
    <b>Device fingerprinting: </b> You need this as well. <a href="#device_fingerprinting">Read below about that</a>.
</p>

<h2 id="device_fingerprinting">Device Fingerprinting &amp; Fraudpointer Service</h2>

<p>
FraudPointer Server has been designed to identify the device that is connected to your server and
is using your e-shop. We can identify it with pretty much high accurracy. This section describes
what you have to do in order to allow FraudPointer Server to work towards this target.
</p>

<h3>HTML Output</h3>

<p><b>After</b> having created an {@link com.fraudpointer.api.models.AssessmentSession AssessmentSession}, you need to serve your customer
the html page with a javascript reference to FraudPointer <code>fp.js</code> script. Hence, the resulting html
output of your, probably, dynamic <code>.jsp</code> page should give something like that:
</p>

<pre>
{@code
<head>
 ......
 <script language="javascript" type="text/javascript" src="https://production.fraudpointer.com/fp.js"></script>
 ......
</head>
}
</pre>

<p>
This script defines the <code>fraudpointer.fp()</code> function that you need to call at some point in time.
A suggestion is to call it after your document is loaded. You can do that as follows:
</p>

<pre>
{@code
<script language="javascript" type="text/javascript">
window.onload = function() {
  fraudpointer.fp(.......);
}
</script>

}
</pre>

<p>
The point here is that you have to pass there the DOM identifier of an <code>input</code> tag of type
<code>hidden</code>. Which by return needs to hold the value of the {@link com.fraudpointer.api.models.AssessmentSession AssessmentSession id}.
Assuming that this <code>hidden input</code> has DOM identifier <c>fp_sid</c>, then the above snippet becomes:
</p>

<pre>
{@code
<script language="javascript" type="text/javascript">
window.onload = function() {
  fraudpointer.fp('fp_sid');
}
</script>
}
</pre>

<p>
We have said that this <code>fp_sid</code> html element needs to have as value the <code>id</code> of the {@link com.fraudpointer.api.models.AssessmentSession AssessmentSession}.
Assuming that this is, for example, <code>34567</code>, html needs to have something like the following, somewhere in its body:
</p>

<pre>
{@code
...
<input type='hidden' id='fp_sid' value='34567'/>
...
}
</pre>

<p>
Summing up, the html output sent to your customer&rsquo;s computer has to contain content similar to the following:
</p>

<pre>
{@code
<html>
<head>
	.... other head stuff goes here .....
	<script language="javascript" type="text/javascript" src="https://production.fraudpointer.com/fp.js"></script>
	<script>
		window.onload = function() {
			fraudpointer.fp('fp_sid');
		}
	</script>
	..... other head stuff goes here .....
</head>
<body>
 .... other body elements go here .....
 <input type="hidden" id="fp_sid" value="34567"/>
 .... other body elements go here .....
</body>
</html>
}
</pre>

<h3>Hint</h3>

<p>
 <b>Using jQuery</b>:
</p>
<p>If you are using <code>jQuery</code> you can always call <code>fraudpointer.fp()</code> function on your <code>document ready</code>
handler. So, instead of writing something like <code>window.onload = function () {.....}</code> as we had above, you can
write something like:
</p>

<pre>
{@code
<script language="javascript" type="text/javascript">
$(document).ready(function() {
    fraudpointer.fp($('#fp_sid').val());
});
</script>

}

*/
package com.fraudpointer.api;
