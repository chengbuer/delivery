




// var query = {
//
//     URL: {
//         taskQuery: function() {
//             return "/delivery/taskQuery";
//         }
//     },
//
//     timeQuery: function () {
//
//         var task = {
//             lng:parseFloat($('#lng').val()),
//             lat:parseFloat($('#lat').val()),
//             beginTime:parseFloat($('#begin_time').val()),
//             endTime:parseFloat($('#end_time').val()),
//             radius:parseFloat($('#radius').val()),
//             reward:parseFloat($('#reward').val()),
//             type:$('#type').val(),
//             label: "task"
//         }
//
//         var bounds = map.getBounds();
//         var sw = bounds.getSouthWest();
//         var ne = bounds.getNorthEast();
//
//         var taskQuery = {
//             lngS: sw.lng,
//             lngE: ne.lng,
//             latS: sw.lat,
//             latE: ne.lat,
//             taskInfo: task
//         }
//
//         // 这里只需要把 task 传递给后台
//         query.drawSchedule(taskQuery);
//
//
//     },
//
//     transit:null,
//
//     // 初始化路径生成器
//
//     // 初始化地图
//
//
//     drawSchedule: function (taskQuery) {
//
//         $.ajax({
//             url:query.URL.taskQuery(),
//             type:"POST",
//             data:JSON.stringify(taskQuery),
//             contentType:"application/json; charset=utf-8",
//             dataType:"json",
//             success:function(worker){
//                 // 这里要将原来的 overlay 擦除，将新的overlay 添加上去
//                 console.log(worker)
//                 // 取出这段序列
//                 var route = worker.schedule;
//                 var points = new Array();
//                 var len = route.length;
//
//                 var start = new BMap.Point(route[0].lng, route[0].lat);
//                 var end = new BMap.Point(route[len-1].lng, route[len-1].lat);
//
//
//
//                 for(var i = 1; i < route.length - 1; i++){
//                     points.push(new BMap.Point(route[i].lng, route[i].lat));
//                 }
//                 console.log(start);
//                 console.log(points);
//                 console.log(end);
//
//                 var print = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
//
//                 print.search(start, end,{waypoints:points});//waypoints表示途经点
//
//
//             }
//             // 如果不为空，则画出来
//
//             // 为空，提示
//
//         });
//     }
// }