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
                console.log(key[1])

                var workerInfo = {
                    workerId: key[0],
                    lng: key[1].lng,
                    lat: key[1].lat,
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
                console.log(route);
                var points = [];
                var len = route.length;

                var start = new BMap.Point(route[0].lng, route[0].lat);
                var end = new BMap.Point(route[len-1].lng, route[len-1].lat);



                for(var i = 1; i < route.length - 1; i++){
                    points.push(new BMap.Point(route[i].lng, route[i].lat));
                }



                var drv = drivingRoutes.get(routes.data.workerId);
                console.log(drv.moveRoute);

                clearInterval(drv.moveRoute);

                drv.eventCompleted = 0;
                console.log(drv.eventCompleted)
                map.removeOverlay(drv.carMk)

                var myIcon =new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)})
                var point = new BMap.Point(routes.data.lng, routes.data.lat);
                drv.carMk = new BMap.Marker(point, {icon:myIcon});
                map.addOverlay(drv.carMk);
                drv.search(start, end,{waypoints:points});


            });
        },

        uploadMultiTask:function(event){
            console.log("Hello world");

            let file = event.target.files[0];
            let param = new window.FormData(); //创建form对象
            param.append('file',file);//通过append向form对象添加数据
            console.log(param.get('file'));

            axios({
                method:'POST',
                url:this.uploadTasks,
                data:param,
                headers: { 'Content-Type': 'multipart/form-data' }
            }).then(function(data){
                // 把所有的schedule 画出来
                console.log(data);
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

                drv.eventCompleted = 0;

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

