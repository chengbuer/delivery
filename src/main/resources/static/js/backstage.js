var workerForm = new Vue({
    el:"#worker",
    data:{
        id : 1,
        lng : 116.404,
        lat : 39.915,
        capacity : 3,
        reward : 0.5,
        radius : 20,
        addWorker:"/worker/add"
    },

    methods:{
        submitWorker:function(event){
            var worker = {
                id : this.id,
                lng : this.lng,
                lat : this.lat,
                capacity : this.capacity,
                reward : this.reward,
                radius : this.radius,
            }

            axios({
                method:'PUT',
                url:this.addWorker,
                data: worker
            }).then(function(resp){
                console.log(resp.data);
                alert("提交worker 成功")
            });
        }
    }
});

var pointOfInterestForm = new Vue({
    el:"#pointOfInterest",
    data:{
        id : 1,
        lng : 116.404,
        lat : 39.915,
        reward : 0.5,
        types : "1,2,3,5",
        addPointOfInterest : "/poI/add"
    }
    ,
    methods:{
        submitPointOfInterest:function(data){
            var pointOfInterest = {
                id : this.id,
                lng : this.lng,
                lat : this.lat,
                reward : this.reward,
                types : this.types,
            }

            axios({
                method:'PUT',
                url:this.addPointOfInterest,
                data: pointOfInterest
            }).then(function(resp){
                console.log(resp.data);
                alert("提交 pointOfInterest 成功")
            });
        }
    }
});