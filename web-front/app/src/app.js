/**
 * Created by jay on 2016. 12. 30..
 */

function bar_chart_render(x) {
    var ret = [];
    var title = ['x'];
    var starVal = ['Star'];
    var forkVal = ['Fork'];
    var watchVal = ['Watches'];
    var group = ['Star','Fork','Watches'];

    for(var i = 0; i < x.length; i++){
        if(x[i].stars > 0 || x[i].fork > 0 || x[i].watches > 0){
            title.push(x[i].name);
            starVal.push(x[i].stars);
            forkVal.push(x[i].fork);
            watchVal.push(x[i].watches);
        }
    }
    ret.push(title);
    ret.push(starVal);
    ret.push(forkVal);
    ret.push(watchVal);
    ret.push(group);
    console.log('bar_char_data', ret);
    return ret;
}

function pie_chart_render(x) {
    var ret = [];
    for(var i = 0; i < x.length; i++){
        var tmp = [];
        tmp.push(x[i].language);
        tmp.push(x[i].frequency);
        ret.push(tmp);
    }
    console.log('pie_chart_data',ret);
    return ret;
}

function draw_Chart(barChart, pieChart){
    var barChartData = barChart;
    var data = [];
    for(var i = 0; i < 4 ; i++) data.push(barChartData[i]);
    var group = barChartData[4];
    var chart = c3.generate({
        bindto:'#chart',
        padding :{
          bottom :50,
        },
        data : {
            x : 'x',
            columns :
            data,
            groups:[
                group
            ],
            type : 'bar'
        },
        zoom: {
            enabled: true
        },
        axis:{
            x: {
                type: 'category'
            }
        }
    });
    $('#chart').append(chart.element);

    var pie = c3.generate({
        bindto : '#pie',
        data :{
            columns :
                pieChart,
            type :'pie'
        }
    });
    $('#pie').append(pie.element);
}



var myApp = angular.module('myApp',['ngRoute'])
    .config(function($routeProvider, $locationProvider, $provide) {
        $locationProvider.html5Mode(true)
        $routeProvider
            .when('/repoList',{
                templateUrl : "repolist/repolist.html",
                controller : "btnCtrl"
            })
    })
    .controller('appCtrl', function($scope,$http,$location) {
        $scope.submitTheForm = function(id) {
            console.log(id);
            $location.path('/repoList');
            $http({
                url: 'http://localhost:8301/api/search',
                method: "GET",
                params : {search : id}
            }).then(function successCallback(response) {
                // this callback will be called asynchronously
                // when the response is available

                var result = response.data;
                $scope.GitHubID = result[0].name;
                $scope.avatar_url = result[0].avatar;
                $scope.repoList = result[1];

                var barChart = bar_chart_render(result[1]);
                var pieChart = pie_chart_render(result[2]);
                draw_Chart(barChart, pieChart);

            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log(response.status);
                console.log(response.data);
            });
        }
    })
    .controller('btnCtrl', function ($scope) {
        $scope.watchClick = function(){
            $scope.bagde = 'watches';
            console.log($scope.bagde);
        };
        $scope.starClick = function(){
            $scope.bagde = 'stars';
            console.log($scope.bagde);
        };
        $scope.forkClick = function(){
            $scope.bagde = 'fork';
            console.log($scope.bagde);
        };
    });