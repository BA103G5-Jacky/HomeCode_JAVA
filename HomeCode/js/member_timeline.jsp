<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Timeline</title>

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Floating button css - power by materializecss -->

      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<!-- Floating button css ..... end -->

  
<!-- # linking piont tab-icon-->
    <link rel="icon" type="images/gif" href="img/hc_icon_w.png" sizes="16x16">      

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/animatecss/3.5.2/animate.min.css" />
    <link rel="stylesheet" href="css/style-albe-timeline.css" />



      
      
        <title>HomeCode!</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" type="text/css" href="css/style-timeline.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
            
<!-- # linking piont bootstrap-lightbox-->  
            <link rel="stylesheet" href="bootstrap-lightbox/bootstrap-lightbox.css">




</head>
<body>
 <!-- nav bar 開始======================================================================================= -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">選單切換</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
<!-- # linking piont 首頁-->
                    <a class="navbar-brand" href="index.html"><img src="img/homecode2.png" height="35" class="img-va"></a>

                </div>
                
                <!-- 手機隱藏選單區 -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    
                    <!-- 左選單 -->
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="collapse-toggle" data-toggle="dropdown">接手案件 <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">申請中案件</a></li>
                                <li><a href="member_case.html">進行中案件</a></li>
                                <li><a href="#">已完成案件</a></li>
                                <li><a href="#">接案評價</a></li>
                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="collapse-toggle" data-toggle="dropdown">發包案件 <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="member_post.html">張貼案件</a></li>
                                <li><a href="#">等待中案件</a></li>
                                <li><a href="member_case.html">進行中案件</a></li>
                                <li><a href="#">已完成案件</a></li>
                                <li><a href="#">發案評價</a></li>

                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="collapse-toggle" data-toggle="dropdown">訊息中心 <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">HomeCode! 訊息</a></li>
                                <li><a href="#">會員訊息</a></li>
                            </ul>
                        </li>
                    </ul>
                    
                    <form class="navbar-form navbar-left ng-pristine margin01">
                        <div class="input-group">
                            <div class="dropdown input-group-btn collapse-toggle">
                                <label class="btn dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-search "></span>
                                    <span class="glyphicon glyphicon-chevron-down "></span>
                                </label>
                                <ul class="dropdown-menu">
                                      <li><a href="#">尋找案件</a></li>
                                      <li><a href="#">尋找人才</a></li>
                                </ul>
                            </div>
                            <input type="text" class="form-control" placeholder=" 找設計人才/工作案件">
                        </div>
                    </form>
                    
                    <!-- 右選單 -->
                    <ul class="nav navbar-nav navbar-right">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="collapse-toggle glyphicon glyphicon-question-sign navicon" data-toggle="dropdown"></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">如何接案</a></li>
                                    <li><a href="#">如何發案</a></li>
                                    <li><a href="#">客服中心</a></li>
                                    <li><a href="#">合約範本下載</a></li>
                                    <li><a href="#">App下載</a></li>
                                </ul>
                            </li>
                        </ul>
                        <li><a href="#" class="glyphicon glyphicon-bell navicon"> </a></li>
                        <li class="dropdown">
                            <a href="#" class="glyphicon glyphicon-user collapse-toggle" data-toggle="dropdown"> <span class="font-18"> user-name</span> <span class="glyphicon glyphicon-chevron-down"></span></a>
                            <ul class="dropdown-menu">
                                  <li><a href="member_profile.html">user-name</a></li>
                                  <li><a href="#">設定</a></li>
                                  <li><a href="#">登出</a></li>
                            </ul>

                        </li>
                        
                    </ul>
                    
                </div>
                <!-- 手機隱藏選單區結束 -->
            </div>
        </nav>
        
<!-- ==nav bar 結束======================================================================================= -->


    <div class="container mrg-top-15 mb20">
           <div class="row">
               <a href="member_case.html"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">案件詳情</div></a>
               <a href="member_case-progress.html"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4">案件進度</div></a>
               <a href="#"><div class="col-xs-12 col-sm-4 btn btn-default central btn-tab-4 active">Timeline</div></a>              
          </div> <!-- Row End -->
       </div> <!-- container mrg-top-15 width-75per End -->


   <div class="hiw"> 
        <div class="container mrg-top-15 width-75per">
           <div class="row">   
                
              <div class="container width-100px">
                 <div class="row">
                      <h2>3D人體解剖學的教學軟體維護</h2>
                 </div>
              </div>


               <div class="container mrg-bottom-15 width-90per" >
                   <div class="row">
                       <div id="myTimeline">
                           <ul id="timeline-menu">
                             <li><a href="#y2014">2014</a></li>
                             <li><a href="#y2016">2016</a></li>
                             <li><a href="#y2017">2017</a></li>
                           </ul>
                           <section id="timeline">
                                   <div id="y2017" class="group2017">2017</div>
                                   <article id="a2016-1" class=" inverted animated fadeInUp">
                                       <div class="panel"><div class="badge">20 <br>Jan</div>
                                           <div class="panel-body">
                                               <img src="img/qrcode.png" width="150px" class="img-responsive">
                                               <h2>里程碑三更新完成：階段三金額已匯入</h2>
                                               <p>筆出家李路們三到整。工學善，情易都縣作論分很是太假石……前氣導精：品經所港考！個減失，引要手說保會沒心票界氣魚完……解才眾習為很可族確合在自一算我一中利出今公，比他完上態；知做小衣深一係教道富權只？治起論雨師重西大給依亞了太處壓個跑學方表一向房主升跟在氣中子第已告……理度。
                                                   <a href="https://github.com/Albejr/jquery-albe-timeline" target="_blank" class="mylink">more details</a>
                                               </p>
                                           </div>
                                       </div>
                                   </article>
       
                                   <div id="y2016" class="group2016">2016</div>
                                   <article id="a2015-1" class="inverted animated fadeInUp">
                                       <div class="panel"><div class="badge">15 <br>Abr</div>
                                           <div class="panel-body">
                                               <h2>里程碑二更新完成：階段二金額已匯入</h2>
                                               <p>國白是式雙長境孩那人的。因只找解；科說獲不國發充件態可作，名白北車民土我道公！充難回小人：科什作紅是素出科錯民眼用、可布上雜風環得年名學道回成，時建候作海是說不！市必獎進小集進親；夠必類絕飯男決發天可論市點二許則來四從也好期吃足人拿得說性那學。
                                               </p>
                                           </div>
                                   </div>
                                   </article>
                                   <article id="a2015-2" class="inverted animated fadeInUp">
                                       <div class="panel">
                                           <div class="badge">29 <br>Mar</div>
                                           <div class="panel-heading">
                                               <h4 class="panel-title">里程碑一更新完成：階段一金額已匯入</h4>
                                           </div>
                                          <!--  <div class="panel-body">
                                               <h1>Lorem ipsum</h1>
                                               <p>Lorem ipsum dolor sit amet, nisl lorem, wisi egestas orci tempus class massa, suscipit eu elit urna in urna, gravida wisi aenean eros massa, cursus quisque leo quisque dui.</p>
                                           </div>
                                           <div class="panel-footer">Sample of footer. See 
                                               <a href="https://github.com/Albejr/jquery-albe-timeline" target="_blank">more details</a>
                                           </div> -->
                                       </div>
                                   </article>
       
                                   <div id="y2014" class="group2014">2014</div>
                                   <article id="a2013-1" class="inverted animated fadeInUp">
                                       <div class="panel">
                                           <div class="badge">20 <br>Jan</div>
                                           <div class="panel-body">
                                               <img src="img/girl.png" width="100px" class="img-responsive">
                                               <h2>歡迎使用Timeline記事本</h2>
                                               <p>Timeline記事本將會協助您專案開發的所有記事功能，舉凡已更新的進度日期會自動更新在Timeline中，您也可以自行加入與合作人的重要記事，幫助您專案的歷史記錄管理。</p>
                                           </div>
                                       </div>
                                   </article>
                                   <article class="animated fadeInUp">
                                       <div class="panel">
                                           <div class="badge">&nbsp;</div>
                                       </div>
                                   </article>
                               <div class="clearfix" style="float: none;"></div>
                           </section> <!-- #timeline End -->
                       </div> <!-- Timeline End -->

                        <!-- add floating button here -->
                        <div class="fixed-action-btn">
                          <a href='#modal-id' data-toggle="modal" class="btn-floating btn-large waves-effect waves-light purple"><i class="material-icons">add</i></a>
                        </div>

                        
                        <div class="modal fade" id="modal-id" style=" height:400px;">
                         
                              <div class="modal-header" >                              
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">新增Timeline內容</h4>
                              </div>
                              <div class="modal-body">

                              <Form Action=" " Method=" " Name=" ">
                                  <div class="form-group">
                                    <label for="timeline-title"><h4>Timeline標題:</h4></label>
                                    <input type="text" class="" id="timeline-title">
                                  </div>
                                  <div class="form-group">
                                    <label for="timeline-date"><h4>Timeline日期:</h4></label>
                                    <input type="date" class="" id="timeline-date">
                                  </div>
                                  <div class="form-group">
                                    <label for="timeline-content"><h4>Timeline內容:</h4></label>
                                    <textarea style="height:20em;" type="text" class="" id="timeline-content"></textarea>
                                  </div>
                                  <div class="form-group">
                                    <label for="timeline-upload"></label>
                                    <input type="file" id="timeline-upload">
                                  </div>
                                 
                                  

                                  </div>
                                    <div class="modal-footer">
                                      <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
                                      <button type="submit" class="btn btn-primary purple">儲存</button>
                                  </div>
                              </Form>

                             
                            
                          
                        </div>

                   </div> <!-- Row End -->
               </div> <!-- container mrg-bottom-15 width-90per End -->
           </div> <!-- Row End -->
       </div> <!-- container mrg-top-15 width-75per End -->
    </div> <!-- hiw End -->
    
<!-- footer區 =========================================================== -->
        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-3">
                        <h3>title</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem provident dolorum veniam, iure voluptate veritatis a debitis architecto iste deserunt aperiam consequatur, officia sequi dolore. Odio, quam ratione magnam fuga itaque? Dicta libero mollitia voluptates neque molestias, harum magnam amet.</p>
                    </div>
                    <div class="col-xs-12 col-sm-3">
                        <h3>title</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Deleniti sapiente doloribus repellendus exercitationem optio veniam quidem porro quis eaque, hic corrupti nobis voluptatum praesentium nemo cum est expedita minus temporibus, commodi fuga nisi. Quis velit, enim earum, provident ab odit!</p>
                    </div>
                    <div class="col-xs-12 col-sm-3">
                        <h3>title</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Excepturi illo distinctio possimus quas et fuga ad enim quod eius iure inventore perspiciatis obcaecati eveniet at consequatur sed animi, itaque sapiente necessitatibus vel quam illum esse exercitationem culpa. Beatae, nisi inventore?</p>
                    </div>
                    <div class="col-xs-12 col-sm-3">
                        <h3>title</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quia voluptatem necessitatibus quaerat iste ad aliquam earum soluta vitae corporis, nulla sed iusto odit, sunt molestias exercitationem quibusdam ex veniam deserunt beatae? Possimus fugiat cum omnis blanditiis ab ipsum, deleniti facilis!</p>
                    </div>
                </div>
            </div>
        </div>
<!-- footer區結束 =========================================================== -->

<!-- script session -->

    <!-- floating button js start -->
      <!--Import jQuery before materialize.js-->
      <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.js"></script>
    <!-- floating button js end -->

    <script src="https://cdn.jsdelivr.net/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/data-albe-timeline.js"></script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>