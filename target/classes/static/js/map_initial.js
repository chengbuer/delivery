
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
                console.log(workers.data);

                for (var i = 0; i < workers.data.length; i++) {

                    var drv = new BMap.DrivingRoute('北京', {renderOptions:{map: map, autoViewport: true}});
                    drv.setMarkersSetCallback(function(pois){

                        markers = [];
                        for(var i = 1; i < pois.length - 1; i++){
                            markers.push(pois[i].point);
                        }

                        var plan = drv.getResults().getPlan(0);
                        var pts =[];
                        for(var j=0;j<plan.getNumRoutes();j++){
                            var route = plan.getRoute(j);
                            pts= pts.concat(route.getPath());
                        }


                        var paths = pts.length;    //获得有几个点
                        var carMk = new BMap.Marker(pts[0],{icon:myIcon});

                        map.addOverlay(carMk);
                        i=0;
                        var passedEvent = 0;
                        function resetMkPoint(i){

                            // console.log(drv.lng)
                            if(i < paths){
                                carMk.setPosition(pts[i]);
                                drv.lng = pts[i].lng;
                                drv.lat = pts[i].lat;
                                if(passedEvent < markers.length && pts[i].lng == markers[passedEvent].lng && pts[i].lat == markers[passedEvent].lat){
                                    console.log("Hello");
                                    passedEvent++;
                                }
                                setTimeout(function(){
                                    i++;
                                    resetMkPoint(i);
                                },100);
                            }else{
                                passedEvent++;
                                console.log(passedEvent);
                            }
                        }
                        setTimeout(function(){
                            resetMkPoint(7);
                        },100)


                    });

                    drv.id = workers.data[i].id;
                    drv.lng = workers.data[i].lng;
                    drv.lat = workers.data[i].lat;
                    drivingRoutes.set(workers.data[i].id, drv);

                    // console.log(drv.id)
                    var point = new BMap.Point(workers.data[i].lng, workers.data[i].lat);
                    var marker = new BMap.Marker(point);
                    map.addOverlay(marker);
                }
                console.log(workers.data);
                console.log(drivingRoutes)
            });
        },
    }
});