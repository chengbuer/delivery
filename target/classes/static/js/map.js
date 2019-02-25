var bMap = {
    URL:{
        pointInterest:function(){
            return "/initial/pointOfInterests";
        },

        worker:function(){
            return "/initial/workers";
        }
    },

    init:function (map) {

        // 创建Map实例
        map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
        //添加地图类型控件
        map.addControl(new BMap.MapTypeControl({
            mapTypes:[
                BMAP_NORMAL_MAP,
                BMAP_HYBRID_MAP
            ]}));
        map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    },

    initPointInterest:function(map){
        var bounds = map.getBounds();
        var sw = bounds.getSouthWest();
        var ne = bounds.getNorthEast();


        $.post(bMap.URL.pointInterest(),  {lngS: sw.lng, lngE: ne.lng, latS: sw.lat, latE:ne.lat}, function (points) {

            for (var i = 0; i < points.length; i ++) {
                var point = new BMap.Point(points[i].lng, points[i].lat);

                var myIcon = new BMap.Icon(bMap.URL.workerImage(), new BMap.Size(300,157));
                var marker = new BMap.Marker(point, {icon:myIcon});

                map.addOverlay(marker);
            }


        });
    },

    initTask:function(map){
        var bounds = map.getBounds();
        var sw = bounds.getSouthWest();
        var ne = bounds.getNorthEast();

        $.post(bMap.URL.task(), {lngS: sw.lng, lngE: ne.lng, latS: sw.lat, latE:ne.lat}, function (points) {
            for (var i = 0; i < points.length; i ++) {
                var point = new BMap.Point(points[i].lng, points[i].lat);
                var marker = new BMap.Marker(point);
                map.addOverlay(marker);
            }
        });
    },

    initWorker:function(map){
        var bounds = map.getBounds();
        var sw = bounds.getSouthWest();
        var ne = bounds.getNorthEast();

        $.post(bMap.URL.worker(), {lngS: sw.lng, lngE: ne.lng, latS: sw.lat, latE:ne.lat}, function (points) {
            for (var i = 0; i < points.length; i ++) {
                var point = new BMap.Point(points[i].lng, points[i].lat);
                var marker = new BMap.Marker(point);
                map.addOverlay(marker);
            }
        });
    }
}
