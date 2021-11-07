import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';

class ScrollNotificationTestRoute extends StatefulWidget {
  @override
  _ScrollNotificationTestRouteState createState() =>
      _ScrollNotificationTestRouteState();
}

const tag = "TestScrollNotification";
class _ScrollNotificationTestRouteState
    extends State<ScrollNotificationTestRoute> {
  String _progress = "0%";

  @override
  Widget build(BuildContext context) {
    LogUtils.d(tag, "build progress: " + _progress);
    return Scrollbar(
      child: NotificationListener<ScrollNotification>(
        onNotification: (ScrollNotification notification){
          double progress = notification.metrics.pixels /
              notification.metrics.maxScrollExtent;
          //重新构建
          setState(() {
            _progress = "${(progress * 100).toInt()}%";
          });

          //LogUtils.d(tag,"BottomEdge: ${notification.metrics.extentAfter == 0}");
          return false;
          //return true; //放开此行注释后，进度条将失效
        },
        child: Stack(
          alignment: Alignment.center,
          children: [
            ListView.builder(
                itemCount: 100,
                itemExtent: 50,
                itemBuilder: (ctx,index){
                  return ListTile(title: Text("this is item: $index"),);
                }),

            CircleAvatar(
              //显示进度百分比
              radius: 30.0,
              child: Text(_progress),
              backgroundColor: Colors.black54,
            )
          ],
        ),
      ),
    );
  }
}