

import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/shijian/touch_event/test_event_bus.dart';
import 'package:flutter_lrn/shijian/touch_event/test_login_widget.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class ReceivedBusWidget extends TemplateRoute {
  ReceivedBusWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) => MyWidget();
}

class MyWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyWidgetState();

}

class MyWidgetState extends State<MyWidget> {
  bool _login = false;
  @override
  void initState() {
    super.initState();
    bus.on("login", (arg) {
      LogUtils.dd("on Login: " + arg);
      setState(() {
        _login = true;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          Text("bus事件: " + (_login == true? "登录成功" : "未登录")),

          Padding(
            padding: EdgeInsets.all(10),
            child:  ElevatedButton(
                onPressed: (){
                  Navigator.push(
                      context,
                      MaterialPageRoute(builder: (ctx){
                        return LoginWidget("登录");
                      })
                  );
                },
                child: Text("跳转登录")
            ) ,
          ),

        ],
      ),
    );
  }

}
