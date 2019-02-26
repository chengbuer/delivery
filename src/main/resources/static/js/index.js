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
        taskQuery:"/schedule/taskQuery"
    },

    methods:{
        hello:function () {
            alert("Hello world")
        },

        arrangeTask:function(){


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

            console.log(task)

            axios({
                method:'POST',
                url:this.taskQuery,
                data:task
            }).then(function(scheudle){
                console.log(scheudle);
            });
        }
    }
});
