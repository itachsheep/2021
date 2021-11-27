import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TestClipWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestClipState();
}

class TestClipState extends State<TestClipWidget> {
  Widget image = Image.asset("pics/avatar.png", width: 60);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("clip控件"),
      ),
      body: Column(
        children: [
          Padding(padding: EdgeInsets.all(5.0), child: image),
          Padding(padding: EdgeInsets.all(5.0), child: ClipOval(child: image)),
          Padding(
              padding: EdgeInsets.all(5.0),
              child: ClipRRect(
                  borderRadius: BorderRadius.circular(5.0), child: image)),
          Padding(
            padding: EdgeInsets.all(5.0),
            child: Row(
              children: [
                Align(
                  alignment: Alignment.topLeft,
                  widthFactor: 0.5, //宽度设为原来宽度一半，另一半会溢出
                  child: image,
                ),
                const Text(
                  "你好，世界",
                  style: TextStyle(color: Colors.green),
                )
              ],
            ),
          ),
          Padding(
            padding: EdgeInsets.all(5.0),
            child: Row(
              children: [
                ClipRect(
                  child: Align(
                    alignment: Alignment.topLeft,
                    widthFactor: 0.5, //宽度设为原来宽度一半，另一半会溢出
                    child: image,
                  ),
                ),
                const Text(
                  "你好，世界",
                  style: TextStyle(color: Colors.green),
                )
              ],
            ),
          ),
          Row(
            children: [
              Padding(
                  padding: EdgeInsets.all(5.0),
                  child: DecoratedBox(
                    decoration: BoxDecoration(color: Colors.green),
                    child: image,
                  )),

              Padding(
                  padding: EdgeInsets.all(5.0),
                  child: DecoratedBox(
                    decoration: BoxDecoration(color: Colors.green),
                    child: ClipRect(
                      clipper: MyClipper(),
                      child: image,
                    ),
                  )),
            ],
          ),
        ],
      ),
    );
  }
}

class MyClipper extends CustomClipper<Rect> {
  @override
  Rect getClip(Size size) {
    return Rect.fromLTWH(10.0, 15.0, 40.0, 30.0);
  }

  @override
  bool shouldReclip(covariant CustomClipper<Rect> oldClipper) {
    return false;
  }
}
