<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Trump News Home</title>
	<link href="/css/home.css" rel="stylesheet"/>
	<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/js-cookie/2.1.0/js.cookie.js"></script>
</head>
<body>
	<div class="top-banner"></div>
	<div class="top-heading">
		<h2>Trump's Latest News!</h2>
		<img alt="" src="/images/trump-tan.jpg">
	</div>
	<div class="top-banner"></div>
	<h3>Trump Articles</h3>
	<div class="unauthenticated">
		<div>
			Login With Google: <a href="/oauth2/authorization/google">click here</a>
		</div>
	</div>
	<div class="authenticated">
		<div class="bar-section">Logged in as: <span id="user"></span></div>
		<a href="/profile">My Articles</a>
		<button onClick="logout()" class="btn btn-primary">Logout</button>
	</div>
	
	<div class ="news-block">
		<h4 class="web-heading">
			USA Today
			<a href="https://www.usatoday.com/search/?q=trump">View On Site</a>
		</h4>
		<div class="save-button authenticated"><button>Save Articles</button></div>
		<ul class="article-list">
			<c:forEach items="${usatoday}" var="a">
			
				<c:url var="saveLink" value="/save">
					<c:param name="articleTitle" value="${a.title}"></c:param>
					<c:param name="articleUrl" value="${a.url}"></c:param>
				</c:url>
				
				<li>
				<a href="${a.url}">${a.title} 	Date: ${a.dateposted} </a>
				<a href="${saveLink}">Save Article</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class ="news-block">
		<h4 class="web-heading">
			NY Times 
			<a href="https://www.nytimes.com/search?query=trump">View On Site</a>
		</h4>
		<ul class="article-list">
			<c:forEach items="${nytimes}" var="a">
				<li>
				<a href="${a.url}">${a.title}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class ="news-block">
		<h4 class="web-heading">
			Fox News
			<a href="https://www.foxnews.com/category/person/donald-trump">View On Site</a>
		</h4>
		<ul class="article-list">
			<c:forEach items="${foxnews}" var="a">
				<li>
				<a href="${a.url}">${a.title}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		$.get("/user", function(data) {
				if (data.name != null ){
					$("#user").html(data.name);
			        $(".unauthenticated").hide();
			        $(".authenticated").show();
				}
		    });
		
		var logout = function(){
			var result = confirm("Are you sure you want to logout?")
			if (result){
				$.post("/logout", function() {
					$("#user").html('');
					$(".unauthenticated").show();
					$(".authenticated").hide();
				})
				return true;
			}
		}
		
		$.ajaxSetup({
			  beforeSend : function(xhr, settings) {
			    if (settings.type == 'POST' || settings.type == 'PUT'
			        || settings.type == 'DELETE') {
			      if (!(/^http:.*/.test(settings.url) || /^https:.*/
			        .test(settings.url))) {
			        // Only send the token to relative URLs i.e. locally.
			        xhr.setRequestHeader("X-XSRF-TOKEN",
			          Cookies.get('XSRF-TOKEN'));
			      }
			    }
			  }
			});
	</script>

</body>
</html>