
var bMap= new Vue({
    data:{
        pointOfInterests: "/initial/pointOfInterests",
        workers: "/initial/workers",
    },

    methods:{
        initMap: function (map) {

            map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别

            map.addControl(new BMap.MapTypeControl({
                mapTypes:[
                    BMAP_NORMAL_MAP,
                    BMAP_HYBRID_MAP
                ]}));
            map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
            map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        },



        initPointOfInterests:function(map){
            var bounds = map.getBounds();
            var sw = bounds.getSouthWest();
            var ne = bounds.getNorthEast();

            var llRange={
                lngStart:sw.lng,
                lngEnd:ne.lng,
                latStart:sw.lat,
                latEnd:ne.lat
            }

            console.log(llRange)

            axios({
                method:'POST',
                url:this.pointOfInterests,
                data:llRange
            }).then(function(poIs){
                for (var i = 0; i < poIs.data.length; i++) {
                    console.log("hello world")
                    var point = new BMap.Point(poIs.data[i].lng, poIs.data[i].lat);
                    var marker = new BMap.Marker(point);
                    map.addOverlay(marker);
                }
                console.log(poIs.data);
            });
        },

        initWorkers:function(map, drivingRoutes){
            var bounds = map.getBounds();
            var sw = bounds.getSouthWest();
            var ne = bounds.getNorthEast();

            var llRange={
                lngStart:sw.lng,
                lngEnd:ne.lng,
                latStart:sw.lat,
                latEnd:ne.lat
            }

            //console.log(llRange)

            function resetMkPoint(i,len,pts,carMk){
                carMk.setPosition(pts[i]);
                if(i < len){
                    setTimeout(function(){
                        i++;
                        resetMkPoint(i,len,pts,carMk);
                    },100);
                }
            }


            axios({
                method:'POST',
                url:this.workers,
                data:llRange
            }).then(function(workers){
                for (var wIdx = 0; wIdx < workers.data.length; wIdx++) {
                    var drv = new BMap.DrivingRoute('北京', {renderOptions:{map: map, autoViewport: true}});
                    var myIcon =new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/Mario.png", new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)})

                    var point = new BMap.Point(workers.data[wIdx].lng, workers.data[wIdx].lat);
                    drv.carMk = new BMap.Marker(point, {icon:myIcon});
                    map.addOverlay(drv.carMk);

                    drv.id = workers.data[wIdx].id;
                    drv.lng = workers.data[wIdx].lng;
                    drv.lat = workers.data[wIdx].lat;
                    drivingRoutes.set(workers.data[wIdx].id, drv);
                    drv.eventCompleted = 0;


                    drv.setMarkersSetCallback(function(pois){
                        drv.markers = [];
                        console.log(pois);
                        for (var j = 1; j < pois.length - 1; j++) {
                            drv.markers.push(pois[j].point);
                        }
                    });

                    drv.setSearchCompleteCallback(function(res) {

                        var plan = res.getPlan(0);
                        var pts = [];
                        for (var j = 0; j < plan.getNumRoutes(); j++) {
                            var route = plan.getRoute(j);
                            pts = pts.concat(route.getPath());
                        }

                        var paths = pts.length;    //获得有几个点

                        var i = 0;

                        drv.lushu = new BMapLib.LuShu(map,pts,{
                            defaultContent:"", //"从天安门到百度大厦"
                            autoView:false,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                            icon  : new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
                            speed: 300,
                            enableRotation:true,//是否设置marker随着道路的走向进行旋转
                            landmarkPois: [
                                {lng:116.314782,lat:39.913508,html:'加油站',pauseTime:2},
                                {lng:116.315391,lat:39.964429,html:'高速公路收费<div><img src="http://map.baidu.com/img/logo-map.gif"/></div>',pauseTime:3},
                                {lng:116.381476,lat:39.974073,html:'肯德基早餐<div><img src="http://ishouji.baidu.com/resource/images/map/show_pic04.gif"/></div>',pauseTime:2}
                            ]});

                        drv.lushu.start();
                       // drv.lushu.showInfoWindow();
                    });
                }
                console.log(workers.data);
                console.log(drivingRoutes)
            });
        },
    }
});










// markers = [];
// console.log(pois);
// for (var j = 1; j < pois.length - 1; j++) {
//     markers.push(pois[j].point);
// }
//
// console.log(drv.getResults().getPlan(0));
//
// var plan = drv.getResults().getPlan(0);
// var pts = [];
// for (var j = 0; j < plan.getNumRoutes(); j++) {
//     var route = plan.getRoute(j);
//     pts = pts.concat(route.getPath());
// }
//
//
// var paths = pts.length;    //获得有几个点
//
// var i = 0;
// drv.moveRoute = setInterval(resetMkPoint, 100);
//
// function resetMkPoint() {
//     if (i < paths) {
//         drv.carMk.setPosition(pts[i]);
//         drv.lng = pts[i].lng;
//         drv.lat = pts[i].lat;
//         if (drv.eventCompleted < markers.length && pts[i].lng == markers[drv.eventCompleted].lng && pts[i].lat == markers[drv.eventCompleted].lat) {
//             drv.eventCompleted++;
//             console.log(drv.eventCompleted);
//         }
//         i++;
//     } else {
//         drv.eventCompleted++;
//         clearInterval(drv.moveRoute);
//     }
// }
// });
//
// var point = new BMap.Point(workers.data[wIdx].lng, workers.data[wIdx].lat);
// drv.carMk = new BMap.Marker(point, {icon:myIcon});
// map.addOverlay(drv.carMk);
//
// }
// console.log(workers.data);
// console.log(drivingRoutes)