(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{40:function(e,t,n){e.exports=n(90)},45:function(e,t,n){},87:function(e,t,n){},90:function(e,t,n){"use strict";n.r(t);var i=n(1),a=n.n(i),s=n(4),o=n.n(s),r=(n(45),n(19)),l=n(7),c=n(8),u=n(10),d=n(9),h=n(11),m=n(5),p=(n(87),function(e){function t(e){var n;return Object(l.a)(this,t),(n=Object(u.a)(this,Object(d.a)(t).call(this,e))).state={visible:!1},n.show=function(){n.setState({visible:!0})},n.hide=function(){n.setState({visible:!1})},n.logIn=function(){n.hide();n.props.setLoginStatus(!0)},n.username=a.a.createRef(),n.password=a.a.createRef(),n}return Object(h.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){var e=this.state.visible,t=[];return t.push(a.a.createElement(m.b,{flat:!0,secondary:!0,swapTheming:!0,onClick:this.hide},"Cancel")),t.push(a.a.createElement(m.b,{flat:!0,primary:!0,swapTheming:!0,onClick:this.logIn},"Confirm")),a.a.createElement("div",null,a.a.createElement(m.b,{flat:!0,secondary:!0,swapTheming:!0,onClick:this.show},"Sign In"),a.a.createElement(m.g,{id:"simple-action-dialog",visible:e,onHide:this.hide,actions:t,title:"Sign In"},a.a.createElement(m.l,{id:"Username",placeholder:"Username",ref:this.username}),a.a.createElement(m.l,{id:"password",label:"Enter your password",type:"password",ref:this.password})))}}]),t}(i.PureComponent)),f=function(e){function t(){return Object(l.a)(this,t),Object(u.a)(this,Object(d.a)(t).apply(this,arguments))}return Object(h.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){var e=this.props.data.map(function(e){return e.title});return a.a.createElement(m.a,{id:"floating-center-title",lineDirection:"center",placeholder:"Search",leftIcon:a.a.createElement(m.h,null,"search"),className:"md-cell--12",data:e,filter:m.a.caseInsensitiveFilter,onAutocomplete:this.props.onAutocomplete,onChange:this.props.onChange})}}]),t}(i.PureComponent),g=n(13),b=n.n(g),v=n(16),k=function(e){function t(e){var n;return Object(l.a)(this,t),(n=Object(u.a)(this,Object(d.a)(t).call(this,e))).show=function(){n.setState({visible:!0})},n.hide=function(){n.setState({visible:!1})},n.like=function(){!1===n.state.disable?(n.doActionByRel("like"),n.setState(function(e){return{likes:e.likes+1,disable:!0}})):n.state.likes>0&&(n.doActionByRel("dislike"),n.setState(function(e){return{likes:e.likes-1,disable:!1}}))},n.postDate=function(){var e=new Date(n.props.time),t=e.getDate(),i=e.getMonth(),a=e.getFullYear();return e.getHours()+":"+e.getMinutes()+" - "+t+" "+["January","February","March","April","May","June","July","August","September","October","November","December"][i]+" "+a},n.getComments=function(){n.setState({isLoading:!0}),n.doActionByRel("comments").then(function(e){return n.setState({comments:e.content,isLoading:!1})})},n.setComment=function(e){n.setState({newComment:e})},n.postComment=function(){var e={text:n.textField.current.value};n.doActionByRel("addComment",e).then(function(e){return n.setState(function(t){return{comments:[].concat(Object(r.a)(t.comments),[e]),newComment:""}})})},n.editPost=function(){n.hide();var e={id:n.props.id,title:n.titleField.current.value,author:n.authorField.current.value,content:n.contentField.current.value,time:(new Date).getTime()};n.doActionByRel("edit",e).then(function(e){return n.props.editPosts(e)})},n.deleteBlog=function(){n.doActionByRel("delete").then(function(e){n.props.deletePost(n.props.id)})},n.textField=a.a.createRef(),n.titleField=a.a.createRef(),n.authorField=a.a.createRef(),n.contentField=a.a.createRef(),n.state={likes:0,disable:!1,expanded:!1,comments:[],isLoading:!1,visible:!1,newComment:""},n}return Object(h.a)(t,e),Object(c.a)(t,[{key:"showComments",value:function(){return this.state.isLoading?a.a.createElement(m.e,{expandable:!0,style:{textAlign:"left"}},a.a.createElement("p",null,"Loading ...")):0===this.state.comments.length&&!1===this.state.isLoading?a.a.createElement(m.e,{expandable:!0,style:{textAlign:"left"}},a.a.createElement("p",null,"No comments"),this.makeComment()):a.a.createElement(m.e,{expandable:!0,style:{textAlign:"left"}},this.state.comments.map(function(e){return a.a.createElement("p",{key:e.id},e.text)}),this.makeComment())}},{key:"makeComment",value:function(){return a.a.createElement("div",null,a.a.createElement(m.l,{id:"comment-field",placeholder:"New comment:",ref:this.textField,value:this.state.newComment,onChange:this.setComment}),a.a.createElement(m.i,{label:""},a.a.createElement(m.b,{flat:!0,secondary:!0,swapTheming:!0,onClick:this.postComment},"Comment")))}},{key:"menu",value:function(){return this.props.deletePost?a.a.createElement(m.k,{id:this.props.id+"Menu",icon:!0,swapTheming:!0,menuItems:[a.a.createElement(m.j,{key:1,primaryText:"Modify",onClick:this.show}),a.a.createElement(m.j,{key:2,primaryText:"Delete",onClick:this.deleteBlog})],centered:!0},"more_vert"):null}},{key:"render",value:function(){var e=[];return e.push(a.a.createElement(m.b,{flat:!0,primary:!0,swapTheming:!0,onClick:this.hide},"Cancel")),e.push(a.a.createElement(m.b,{flat:!0,secondary:!0,swapTheming:!0,onClick:this.editPost},"Edit")),a.a.createElement("div",null,a.a.createElement(m.c,{onExpanderClick:this.getComments},a.a.createElement(m.f,{style:{textAlign:"left"},title:this.props.title,subtitle:"By: "+this.props.author+" - "+this.postDate()}),a.a.createElement(m.e,{style:{textAlign:"left"}},a.a.createElement("p",null,this.props.content)),a.a.createElement(m.d,{expander:!0},a.a.createElement("p",{style:{margin:"0px",paddingLeft:"8px",paddingRight:"8px"}},this.props.likes+this.state.likes),a.a.createElement(m.b,{icon:!0,secondary:this.state.disable,swapTheming:!0,onClick:this.like},"favorite"),this.menu()),this.showComments()),a.a.createElement(m.g,{id:"new-blog-post",visible:this.state.visible,onHide:this.hide,actions:e,title:"Edit Blog Post"},a.a.createElement(m.l,{id:"title",label:"Title of the post:",required:!0,defaultValue:this.props.title,ref:this.titleField}),a.a.createElement(m.l,{id:"author",label:"Authors name:",required:!0,defaultValue:this.props.author,ref:this.authorField}),a.a.createElement(m.l,{id:"content",label:"Content:",rows:5,required:!0,defaultValue:this.props.content,ref:this.contentField})))}},{key:"doActionByRel",value:function(){var e=Object(v.a)(b.a.mark(function e(t,n){var i,a,s,o;return b.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if((i=this.props.links.find(function(e){return e.rel===t})).href=this.removeDomainFromUrl(i.href),"undefined"!==typeof i){e.next=4;break}throw new Error("Invalid argument provided: rel");case 4:if("undefined"!==typeof n){e.next=20;break}return e.next=7,fetch(i.href,{method:i.type});case 7:return a=e.sent,e.prev=8,e.next=11,a.json();case 11:s=e.sent,e.next=17;break;case 14:e.prev=14,e.t0=e.catch(8),s=[];case 17:return e.abrupt("return",s);case 20:return e.next=22,fetch(i.href,{method:i.type,headers:{"Content-Type":"application/json"},body:JSON.stringify(n)});case 22:return o=e.sent,e.next=25,o.json();case 25:return e.abrupt("return",e.sent);case 26:case"end":return e.stop()}},e,this,[[8,14]])}));return function(t,n){return e.apply(this,arguments)}}()},{key:"removeDomainFromUrl",value:function(e){for(var t=0,n=0;n<e.length-"/api".length;n++)if("/api"===e.substring(n,n+"/api".length)){t=n;break}return e.substring(t)}}]),t}(i.PureComponent),E=function(e){function t(){return Object(l.a)(this,t),Object(u.a)(this,Object(d.a)(t).apply(this,arguments))}return Object(h.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){var e=this;return this.props.data?this.props.data.concat().sort(function(e,t){return new Date(t.time)-new Date(e.time)}).map(function(t){return a.a.createElement("div",{key:t.id,className:"md-block-centered md-cell--10",style:{marginBottom:"16px"}},a.a.createElement(k,{id:t.id,title:t.title,author:t.author,content:t.content,time:t.time,likes:t.likes,links:t.links,likeAction:e.onLikeActionBuilder(t),dislikeAction:e.onDislikeActionBuilder(t),deletePost:e.props.deletePost,editPosts:e.props.editPosts}))}):this.props.singlePost?a.a.createElement("div",{key:this.props.singlePost.id,className:"md-block-centered md-cell--10"},a.a.createElement(k,{id:this.props.singlePost.id,title:this.props.singlePost.title,author:this.props.singlePost.author,content:this.props.singlePost.content,time:this.props.singlePost.time,likes:this.props.singlePost.likes,links:this.props.singlePost.links,likeAction:this.onLikeActionBuilder(this.props.singlePost),dislikeAction:this.onDislikeActionBuilder(this.props.singlePost),deletePost:this.props.deletePost,editPosts:this.props.editPosts})):void 0}},{key:"onDislikeActionBuilder",value:function(e){var t=e.links.find(function(e){return"dislike"===e.rel});return function(){fetch(t.href,{method:"post",mode:"no-cors"})}}},{key:"onLikeActionBuilder",value:function(e){var t=e.links.find(function(e){return"like"===e.rel});return function(){fetch(t.href,{method:"post",mode:"no-cors"})}}}]),t}(i.PureComponent),y=function(e){function t(e){var n;return Object(l.a)(this,t),(n=Object(u.a)(this,Object(d.a)(t).call(this,e))).hide=function(){n.props.hide()},n.newPost=function(){if(0!==n.titleField.current.value.length&&0!==n.authorField.current.value.length&&0!==n.contentField.current.value.length){n.hide();var e=n.props.link;e.href=n.removeDomainFromUrl(e.href);var t={title:n.titleField.current.value,author:n.authorField.current.value,content:n.contentField.current.value,time:(new Date).getTime()};fetch(e.href,{method:n.props.link.type,headers:{"Content-Type":"application/json"},body:JSON.stringify(t)}).then(function(e){return e.json()}).then(function(e){return n.props.updatePage(e)})}},n.titleField=a.a.createRef(),n.authorField=a.a.createRef(),n.contentField=a.a.createRef(),n}return Object(h.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){var e=[];return e.push(a.a.createElement(m.b,{flat:!0,primary:!0,swapTheming:!0,onClick:this.hide},"Cancel")),e.push(a.a.createElement(m.b,{flat:!0,secondary:!0,swapTheming:!0,onClick:this.newPost},"Send")),a.a.createElement("div",null,a.a.createElement(m.g,{id:"new-blog-post",visible:this.props.show,onHide:this.hide,actions:e,title:"New Blog Post",width:500},a.a.createElement(m.l,{id:"title",label:"Title of the post:",required:!0,ref:this.titleField}),a.a.createElement(m.l,{id:"author",label:"Authors name:",required:!0,ref:this.authorField}),a.a.createElement(m.l,{id:"content",label:"Content:",rows:5,required:!0,ref:this.contentField})))}},{key:"removeDomainFromUrl",value:function(e){for(var t=0,n=0;n<e.length-"/api".length;n++)if("/api"===e.substring(n,n+"/api".length)){t=n;break}return e.substring(t)}}]),t}(i.PureComponent),w=function(e){function t(e){var n;return Object(l.a)(this,t),(n=Object(u.a)(this,Object(d.a)(t).call(this,e))).logout=function(){n.props.setLoginStatus(!1)},n.hide=function(){n.setState({show:!1})},n.showNewBlogPost=function(){n.setState({show:!0})},n.state={show:!1},n}return Object(h.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){return a.a.createElement("div",null,a.a.createElement(m.k,{id:this.props.id,icon:!0,className:this.props.className,menuItems:[a.a.createElement(m.j,{key:"new-blog-post",primaryText:"New Post",onClick:this.showNewBlogPost}),a.a.createElement(m.j,{key:"logout",primaryText:"Logout",onClick:this.logout})]},"more_vert"),a.a.createElement(y,{key:"new-blog-post",link:this.props.link,updatePage:this.props.updatePage,show:this.state.show,hide:this.hide}))}}]),t}(i.PureComponent),P=function(e){function t(e){var n;return Object(l.a)(this,t),(n=Object(u.a)(this,Object(d.a)(t).call(this,e))).show=function(){n.setState({visible:!0})},n.hide=function(){n.setState({visible:!1})},n.setLoginStatus=function(e){n.setState({loggedIn:e})},n.onAutocomplete=function(e){var t=!0,i=!1,a=void 0;try{for(var s,o=n.state.posts[Symbol.iterator]();!(t=(s=o.next()).done);t=!0){var r=s.value;r.title===e&&n.setState({post:r})}}catch(l){i=!0,a=l}finally{try{t||null==o.return||o.return()}finally{if(i)throw a}}n.show()},n.onChange=function(e){""===e&&n.hide()},n.editPosts=function(e){var t=n.state.post;n.state.post.id===e.id&&(t=e);var i=Object(r.a)(n.state.posts),a=i.findIndex(function(t){return t.id===e.id});i[a]=e,n.forceUpdate(function(){return n.setState({posts:i,post:t})})},n.deletePost=function(e){var t=n.state.post;n.state.post.id===e&&(t=null,n.hide()),n.forceUpdate(function(){return n.setState({posts:n.state.posts.filter(function(t){return t.id!==e}),post:t})})},n.updatePage=function(e){fetch("/api/posts/"+e.id,{mode:"no-cors",method:"GET"}).then(function(e){return e.json()}).then(function(e){return n.setState(function(t){return{posts:[].concat(Object(r.a)(t.posts),[e])}})})},n.state={posts:null,visible:!1,post:null,createPostLink:null,loggedIn:!1},n}return Object(h.a)(t,e),Object(c.a)(t,[{key:"componentDidMount",value:function(){var e=this;fetch("/api/posts/",{mode:"no-cors",method:"GET"}).then(function(e){return e.json()}).then(function(t){return e.setState({posts:t.content})}),fetch("/api/",{mode:"no-cors",method:"GET"}).then(function(e){return e.json()}).then(function(t){return e.setState({createPostLink:t.links.find(function(e){return"createPost"===e.rel})})})}},{key:"render",value:function(){return this.state.posts?this.state.visible?this.state.loggedIn?a.a.createElement("div",{className:"App"},a.a.createElement(m.m,{themed:!0,title:"Blogging site",actions:a.a.createElement(w,{id:"toolbar-kebab-menu",setLoginStatus:this.setLoginStatus,link:this.state.createPostLink,updatePage:this.updatePage})}),a.a.createElement("div",{className:"md-grid"},a.a.createElement(f,{data:this.state.posts,onAutocomplete:this.onAutocomplete,onChange:this.onChange})),a.a.createElement("div",{className:"md-grid"},a.a.createElement(E,{singlePost:this.state.post,deletePost:this.deletePost,editPosts:this.editPosts}))):a.a.createElement("div",{className:"App"},a.a.createElement(m.m,{themed:!0,title:"Blogging site",actions:a.a.createElement(p,{setLoginStatus:this.setLoginStatus})}),a.a.createElement("div",{className:"md-grid"},a.a.createElement(f,{data:this.state.posts,onAutocomplete:this.onAutocomplete,onChange:this.onChange})),a.a.createElement("div",{className:"md-grid"},a.a.createElement(E,{singlePost:this.state.post}))):this.state.loggedIn?a.a.createElement("div",{className:"App"},a.a.createElement(m.m,{themed:!0,title:"Blogging site",actions:a.a.createElement(w,{id:"toolbar-kebab-menu",setLoginStatus:this.setLoginStatus,link:this.state.createPostLink,updatePage:this.updatePage})}),a.a.createElement("div",{className:"md-grid"},a.a.createElement(f,{data:this.state.posts,onAutocomplete:this.onAutocomplete,onChange:this.onChange})),a.a.createElement("div",{className:"md-grid"},a.a.createElement(E,{data:this.state.posts,deletePost:this.deletePost,editPosts:this.editPosts}))):a.a.createElement("div",{className:"App"},a.a.createElement(m.m,{themed:!0,title:"Blogging site",actions:a.a.createElement(p,{setLoginStatus:this.setLoginStatus})}),a.a.createElement("div",{className:"md-grid"},a.a.createElement(f,{data:this.state.posts,onAutocomplete:this.onAutocomplete,onChange:this.onChange})),a.a.createElement("div",{className:"md-grid"},a.a.createElement(E,{data:this.state.posts}))):a.a.createElement("div",{className:"App"},a.a.createElement(m.m,{themed:!0,title:"Blogging site",actions:a.a.createElement(p,{setLoginStatus:this.setLoginStatus})}),a.a.createElement("p",null,"Loading ..."))}}]),t}(i.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));var C=n(39);n.n(C).a.load({google:{families:["Roboto:300,400,500,700","Material Icons"]}}),o.a.render(a.a.createElement(P,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})}},[[40,1,2]]]);
//# sourceMappingURL=main.babc16c8.chunk.js.map