
var express = require('express');
var mongoose = require('mongoose');

var application = express();

mongoose.connect('mongodb://moose:moose@ds027345.mongolab.com:27345/moth');
application.use(express.static(__dirname + '/public'));

application.listen(8080);
console.log("App listening on 8080.");


