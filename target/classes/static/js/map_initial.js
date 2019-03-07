
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

            var myIcon =new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)})


            axios({
                method:'POST',
                url:this.workers,
                data:llRange
            }).then(function(workers){
                for (var i = 0; i < workers.data.length; i++) {
                    var drv = new BMap.DrivingRoute('北京', {renderOptions:{map: map, autoViewport: true}});
                    drv.id = workers.data[i].id;
                    drv.lng = workers.data[i].lng;
                    drv.lat = workers.data[i].lat;
                    drivingRoutes.set(workers.data[i].id, drv);
                    drv.eventCompleted = 0;
                    drv.setMarkersSetCallback(function(pois) {

                        markers = [];
                        console.log(pois);
                        for (var i = 1; i < pois.length - 1; i++) {
                            markers.push(pois[i].point);
                        }

                        var plan = drv.getResults().getPlan(0);
                        var pts = [];
                        for (var j = 0; j < plan.getNumRoutes(); j++) {
                            var route = plan.getRoute(j);
                            pts = pts.concat(route.getPath());
                        }


                        var paths = pts.length;    //获得有几个点

                        var i = 0;
                        drv.moveRoute = setInterval(resetMkPoint, 100);

                        function resetMkPoint() {
                            if (i < paths) {
                                drv.carMk.setPosition(pts[i]);
                                console.log(pts[i]);
                                drv.lng = pts[i].lng;
                                drv.lat = pts[i].lat;
                                if (drv.eventCompleted < markers.length && pts[i].lng == markers[drv.eventCompleted].lng && pts[i].lat == markers[drv.eventCompleted].lat) {
                                    drv.eventCompleted++;
                                    console.log(drv.eventCompleted);
                                }
                                i++;
                            } else {
                                drv.eventCompleted++;
                                clearInterval(drv.moveRoute);
                            }
                        }
                    });

                    var point = new BMap.Point(workers.data[i].lng, workers.data[i].lat);
                    drv.carMk = new BMap.Marker(point, {icon:myIcon});
                    map.addOverlay(drv.carMk);

                }
                console.log(workers.data);
                console.log(drivingRoutes)
            });
        },
    }
});