
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestFutureBuilderWidget extends TemplateRoute {
  TestFutureBuilderWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return MyFutureBuilderWidget();
  }

}

class MyFutureBuilderWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyFutureBuilderState();

}

class MyFutureBuilderState extends State<MyFutureBuilderWidget> {

  Future<String> mockNetworkData() {
      LogUtils.dd("mockNetworkData -->");
      return Future.delayed(Duration(seconds: 2),(){
        LogUtils.dd("mockNetworkData get result -->");
        return "网络结果：哈哈哈";
      });
  }

  @override
  Widget build(BuildContext context) {
    LogUtils.dd("build -->");
    return Center(
      child: FutureBuilder(
        future: mockNetworkData(),
        builder: (BuildContext ctx,AsyncSnapshot snapshot){
          if(snapshot.connectionState == ConnectionState.done) {
            if (snapshot.hasError) {
              // 请求失败，显示错误
              return Text("Error: ${snapshot.error}");
            } else {
              // 请求成功，显示数据
              return Text("Contents: ${snapshot.data}");
            }
          } else {
            // 请求未结束，显示loading
            return CircularProgressIndicator();
          }
        },
      ),
    );
  }

}