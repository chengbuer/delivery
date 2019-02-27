
var bMap= new Vue({
    data:{
        pointOfInterests: "/initial/pointOfInterests",
        workers: "/initial/workers"
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

            axios({
                method:'POST',
                url:this.workers,
                data:llRange
            }).then(function(workers){
                console.log(workers.data);
                for (var i = 0; i < workers.data.length; i++) {
                    var point = new BMap.Point(workers.data[i].lng, workers.data[i].lat);

                    // 初始化路线

                    var drv = new BMap.DrivingRoute('北京', {
                        onSearchComplete: function(res) {
                            if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
                                var plan = res.getPlan(0);
                                var arrPois =[];
                                for(var j=0;j<plan.getNumRoutes();j++){
                                    var route = plan.getRoute(j);
                                    arrPois= arrPois.concat(route.getPath());
                                }


                                lushu = new BMapLib.LuShu(map,arrPois,{
                                    defaultContent:"",//"从天安门到百度大厦"
                                    autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                                    icon  : new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
                                    speed: 4500,
                                    enableRotation:true,//是否设置marker随着道路的走向进行旋转
                                    landmarkPois: [
                                        {lng:116.314782,lat:39.913508,html:'加油站',pauseTime:2},
                                        {lng:116.315391,lat:39.964429,html:'高速公路收费<div><img src="http://map.baidu.com/img/logo-map.gif"/></div>',pauseTime:3},
                                        {lng:116.381476,lat:39.974073,html:'肯德基早餐<div><img src="http://ishouji.baidu.com/resource/images/map/show_pic04.gif"/></div>',pauseTime:2}
                                    ]});
                                lushu.start();
                            }
                        }
                        ,renderOptions:{map: map, autoViewport: true}});

                    drivingRoutes.set(workers.data[i].id, drv);

                    var marker = new BMap.Marker(point);
                    map.addOverlay(marker);
                }
                console.log(workers.data);
                console.log(drivingRoutes);
            });
        },
    }
});