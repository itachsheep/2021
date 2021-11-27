
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class TestStackWidget extends StatefulWidget {

  @override
  State<StatefulWidget> createState() => TestStackState();

}

class TestStackState extends State<TestStackWidget> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("stack控件"),
      ),

      body: ConstrainedBox(
        constraints: BoxConstraints.expand(),
        child: Stack(
          alignment:Alignment.center , //指定未定位或部分定位widget的对齐方式
          //fit: StackFit.expand,//未定位widget占满Stack整个空间
          children: <Widget>[
            Container(
              child: Text("Hello world",style: TextStyle(color: Colors.white)),
              color: Colors.red,
            ),
            Positioned(
              left: 18.0,
              child: Text("I am Jack"),
            ),
            Positioned(
              top: 18.0,
              left: 50.0,
              child: Text("Your friend"),
            ),

            Positioned(
              top: 30,
              child: Container(
                height: 120.0,
                width: 120.0,
                color: Colors.green,
                child: Align(
                  alignment: Alignment.topRight,
                  child: FlutterLogo(
                    size: 60,
                  ),
                ),
              )
            ),
          ],
        ),
      ),
    );
  }

}