import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class HttpDioRoute extends TemplateRoute {
  HttpDioRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return MyWidget();
  }
}

class MyWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyWigetState();
}

class MyWigetState extends State<MyWidget> {
  Dio _dio = Dio();

  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      child: FutureBuilder(
        future: _dio.get("https://api.github.com/orgs/flutterchina/repos"),
        builder: (BuildContext context, AsyncSnapshot snapshot) {
          //请求完成
          if (snapshot.connectionState == ConnectionState.done) {
            Response response = snapshot.data;

            //发生错误
            if (snapshot.hasError) {
              return Text(snapshot.error.toString());
            }

            //请求成功，通过项目信息构建用于显示项目名称的ListView
            LogUtils.dd("response : ${response.data}");
            LogUtils.dd("-----------------------------");
            return ListView(
              /*children: response.data.map<Widget>((e) =>
                  ListTile(title: Text(e["full_name"]))
              ).toList(),*/
              children: response.data.map<Widget>(
                (e) {
                  LogUtils.dd("response : $e");
                  return ListTile(title: Text(e["full_name"]));
                }
              ).toList(),
            );
          }

          //请求未完成时弹出loading
          return CircularProgressIndicator();
        },
      ),
    );
  }
}
