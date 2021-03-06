var task = new Vue({
    el:"#task",
    data:{
        id:"1",
        lng:116.404,
        lat:39.915,
        beginTime: 0,
        endTime: 20,
        radius: 20,
        type:"1",
        reward:0.5,
        taskQuery:"/schedule/taskQuery",
        locationUpdated:"/schedule/locationUpdated",
        uploadTasks:"/schedule/upload"
    },

    methods:{

        updateWorkerLocations: function (drivingRoutes, map) {
            // 需要以下两个信息
            // workerId
            // lng & lat
            var drivings = [];
            for (var key of drivingRoutes) {
                var curLng;
                var curLat;

                if(key[1].lushu ===  undefined){
                    curLng = key[1].lng;
                    curLat = key[1].lat;
                }else{
                    curLng = key[1].lushu._marker.getPosition().lng;
                    curLat = key[1].lushu._marker.getPosition().lat;
                }

                //console.log(key[1].lushu._marker.getCurrentPosition());

                var workerInfo = {
                    workerId: key[0],
                    lng: curLng,
                    lat: curLat,
                    eventCompleted : key[1].eventCompleted
                }
                drivings.push(workerInfo);
            }

            var task={
                id : this.id,
                lng: this.lng,
                lat: this.lat,
                beginTime:this.beginTime,
                endTime:this.endTime,
                radius:this.radius,
                type:this.type,
                reward:this.reward
            }

            var scheduleUpdate = {
                workerInfos:drivings,
                task:task
            }

            // 提交信息后，在后台更新所有worker 的位置信息
            axios({
                method:'POST',
                url:this.locationUpdated,
                data:scheduleUpdate
            }).then(function(routes) {
                var route = routes.data.schedule;
                var points = [];
                var len = route.length;

                var start = new BMap.Point(route[0].lng, route[0].lat);
                var end = new BMap.Point(route[len-1].lng, route[len-1].lat);

                for(var i = 1; i < route.length - 1; i++){
                    points.push(new BMap.Point(route[i].lng, route[i].lat));
                }

                var drv = drivingRoutes.get(routes.data.workerId);
                if(drv.lushu !== undefined)
                    map.removeOverlay(drv.lushu._marker);

                drv.eventCompleted = 0;
                console.log(drv.eventCompleted)
                map.removeOverlay(drv.carMk);

                // var myIcon =new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)})
                // var point = new BMap.Point(routes.data.lng, routes.data.lat);
                // drv.carMk = new BMap.Marker(point, {icon:myIcon});
                drv.search(start, end,{waypoints:points});
            });
        },

        uploadMultiTask:function(event, drivingRoutes){
            console.log("Hello world");

            let file = event.target.files[0];
            let param = new window.FormData(); //创建form对象
            param.append('file',file);//通过append向form对象添加数据
            console.log(param.get('file'));
            console.log(drivingRoutes.get(1).carMk === drivingRoutes.get(2).carMk);

            axios({
                method:'POST',
                url:this.uploadTasks,
                data:param,
                headers: { 'Content-Type': 'multipart/form-data' }
            }).then(function(schedules){
                var sces = schedules.data;
                for(var i = 0; i < sces.length; i++){

                    var route = sces[i].schedule;
                    var drNo = sces[i].workerId;
                    console.log("当前迭代次数 " + i);
                    console.log(route);

                    if(route.length <= 1) continue;

                    var points = [];
                    var len = route.length;

                    var start = new BMap.Point(route[0].lng, route[0].lat);
                    var end = new BMap.Point(route[len-1].lng, route[len-1].lat);

                    for(var j = 1; j < route.length - 1; j++){
                        points.push(new BMap.Point(route[j].lng, route[j].lat));
                    }
                    var drv = drivingRoutes.get(drNo);


                    //
                    // clearInterval(drv.moveRoute);
                    //
                    // drv.eventCompleted = 0;
                    // map.removeOverlay(drv.carMk)
                    // console.log(sces[i]);
                    //
                    // var myIcon =new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)})
                    // var point = new BMap.Point(sces[i].lng, sces[i].lat);
                    // drv.carMk = new BMap.Marker(point, {icon:myIcon});
                    // map.addOverlay(drv.carMk);
                    //
                    // console.log(drv);

                    drv.search(start, end,{waypoints:points});
                }
            })
        },

        arrangeTask:function(drivingRoutes){

            var task={
                id : this.id,
                lng: this.lng,
                lat: this.lat,
                beginTime:this.beginTime,
                endTime:this.endTime,
                radius:this.radius,
                type:this.type,
                reward:this.reward
            }

            axios({
                method:'POST',
                url:this.taskQuery,
                data:task
            }).then(function(workerInfo){


                var route = workerInfo.data.schedule;
                //console.log(route);
                var points = [];
                var len = route.length;

                var start = new BMap.Point(route[0].lng, route[0].lat);
                var end = new BMap.Point(route[len-1].lng, route[len-1].lat);

                for(var i = 1; i < route.length - 1; i++){
                     points.push(new BMap.Point(route[i].lng, route[i].lat));
                }

                var print = drivingRoutes.get(workerInfo.data.workerId);

                print.search(start, end,{waypoints:points});
            });
        }
    }
});

var taskFile = new Vue({
    el:"#upLoadFile",

    data:{
        multTaskArrange:"/schedule/tasks"
    },

    mehtods:{
        uploadMultiTask:function(event){
            console.log("Hello world");

            let file = event.target.files[0];
            let param = new window.FormData(); //创建form对象
            param.append('file',file);//通过append向form对象添加数据
            console.log(param.get('file'));

        }
    }
});

