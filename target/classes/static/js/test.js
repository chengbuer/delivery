var world=new Vue({
    el: '#app',
    data: {
        counter: 0,
        name:"worldsdf"
    },
    methods: {


        greet: function (event) {

            var llRange = {
                latEnd:40.344846,
                latStart:39.482417,
                lngEnd:116.784594,
                lngStart:116.023406
            }

            axios({
                method:'post',
                url:"/test/jsonTest",
                data:llRange
            }).then(function(resp){
                console.log(resp.data);
            });
        },

        hello: function(world){
            alert("world" + world );
        }

    }
})