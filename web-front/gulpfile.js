/**
 * Created by jay on 2017.03.27
 */

var gulp = require('gulp');
var clean = require('gulp-clean');
var pkg = require('./package.json');
var header = require('gulp-header');
var gulpSync = require('gulp-sync')(gulp);
var browserSync = require('browser-sync');

var minify_html = require('gulp-minify-html');
var rename = require('gulp-rename');
var uglify = require('gulp-uglify');
var cleanCss = require('gulp-clean-css');


var banner = ['/*!\n',
    ' * Pinned - <%= pkg.title %> v<%= pkg.version %> (<%= pkg.homepage %>)\n',
    ' * Copyright 2016-' + (new Date()).getFullYear(), ' <%= pkg.author %>\n',
    ' * Licensed under <%= pkg.license.type %> (<%= pkg.license.url %>)\n',
    ' */\n',
    ''
].join('');

gulp.task('clean-build', function () {
    return gulp.src('./app/build/*')
        .pipe(clean());
});

gulp.task('clean-dist', function(){
   return gulp.src('./app/dist/*')
       .pipe(clean());
});

gulp.task('copy-vendor',function () {

    gulp.src(['./node_modules/angular/angular.min.js','./node_modules/angular-route/angular-route.min.js'])
        .pipe(gulp.dest('./app/build/vendor/angular/'));

    gulp.src(['./node_modules/bootstrap/dist/css/bootstrap.min.css','./node_modules/bootstrap/dist/js/bootstrap.min.js'])
        .pipe(gulp.dest('./app/build/vendor/bootstrap/'));

    gulp.src('./node_modules/font-awesome/css/font-awesome.min.css')
        .pipe(gulp.dest('./app/build/vendor/font-awesome/css/'))

    gulp.src('./node_modules/font-awesome/fonts/*')
        .pipe(gulp.dest('./app/build/vendor/font-awesome/fonts'))

    gulp.src('./node_modules/jquery/dist/jquery.min.js')
        .pipe(gulp.dest('./app/build/vendor/jquery/'));

    gulp.src(['./node_modules/c3/c3.min.js','./node_modules/c3/c3.min.css'])
        .pipe(gulp.dest('./app/build/vendor/c3/'));

    gulp.src('./node_modules/d3/d3.min.js')
        .pipe(gulp.dest('./app/build/vendor/d3/'));

});

gulp.task('copy-searchApp',function () {
    return gulp.src('./app/src/search-app/*')
        .pipe(gulp.dest('./app/build/search-app'));
});

gulp.task('copy-html',function () {
    return gulp.src(['./app/src/*','./app/src/**/*'])
        .pipe(gulp.dest('./app/build/'));
});

gulp.task('browser-sync', function() {
    browserSync.init(null, {
        server : {
            baseDir: './app/dist/'
        },
        port: 7000
    });
});

gulp.task('build', gulpSync.sync(['clean-build','copy-vendor','copy-searchApp','copy-html']));


gulp.task('realise', gulpSync.sync(['clean-dist','build']),function () {
    gulp.src('app/build/vendor/**/**/*')
        .pipe(gulp.dest('app/dist/vendor/'));

    gulp.src('app/build/repolist/*.html')
        .pipe(minify_html())
        .pipe(gulp.dest('app/dist/repolist/'));

    gulp.src('app/build/*.js')
        .pipe(header(banner, { pkg: pkg }))
        .pipe(gulp.dest('app/dist/'));

    gulp.src('app/build/*.css')
        .pipe(cleanCss())
        .pipe(rename({ suffix: '.min' }))
        .pipe(gulp.dest('app/dist/'));

    gulp.src('app/build/index.html')
        .pipe(minify_html())
        .pipe(gulp.dest('app/dist/'));

});

gulp.task('run', gulpSync.sync(['realise','browser-sync']));
