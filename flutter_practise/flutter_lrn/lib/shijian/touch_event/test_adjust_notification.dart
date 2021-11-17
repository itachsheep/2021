import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class AdjustNotificationWidget extends TemplateRoute {
  AdjustNotificationWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) => MyWidget();
}

class MyWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => MyWidgetState();
}

class MyWidgetState extends State<MyWidget> {
  String _msg = "";

  @override
  Widget build(BuildContext context) {
    return NotificationListener<MyNotification>(
        onNotification: (notification) {
          setState(() {
            _msg += notification.msg + "  ";
          });
          return true;
        },
        child: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              //代码中注释的部分是不能正常工作的，
              // 因为这个context是根Context，而NotificationListener是监听的子树，
              // 所以我们通过Builder来构建ElevatedButton，来获得按钮位置的context。
//           ElevatedButton(
//           onPressed: () => MyNotification("Hi").dispatch(context),
//           child: Text("Send Notification"),
//          ),
              Builder(
                builder: (context) {
                  return ElevatedButton(
                    //按钮点击时分发通知
                    onPressed: () => MyNotification("Hi").dispatch(context),
                    child: Text("Send Notification"),
                  );
                },
              ),
              Text(_msg)
            ],
          ),
        ));
  }
}

class MyNotification extends Notification {
  final String msg;

  MyNotification(this.msg);
}
