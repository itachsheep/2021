import 'package:flutter/material.dart';

class TestConstrainWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestConstrainState();
}

class TestConstrainState extends State<TestConstrainWidget> {

  Widget redBox = const DecoratedBox(
    decoration: BoxDecoration(color: Colors.red),
  );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("约束widget"),
        actions:const <Widget>[
          UnconstrainedBox(
            child: SizedBox(
              width: 20,
              height: 20,
              child: CircularProgressIndicator(
                strokeWidth: 3,
                valueColor: AlwaysStoppedAnimation(Colors.white70),
              ),
            ),
          )
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ConstrainedBox(
              constraints: const BoxConstraints(
                  minWidth: double.infinity, minHeight: 50.0),
              child: Container(
                  height: 5.0,
                  child: redBox,)
            ),

            SizedBox(
              width: 50.0,
              height: 50.0,
              child: redBox,
            ),


            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children:const <Widget>[
                Text(" hello world "),
                Text(" I am Jack "),
              ],
            ),
            Row(
              mainAxisSize: MainAxisSize.min,
              mainAxisAlignment: MainAxisAlignment.center,
              children:const <Widget>[
                Text(" hello world "),
                Text(" I am Jack "),
              ],
            ),

            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              textDirection: TextDirection.rtl,
              children:const <Widget>[
                Text(" hello world "),
                Text(" I am Jack "),
              ],
            ),
            Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              verticalDirection: VerticalDirection.down,
              children:const  <Widget>[
                Text(" hello world ", style: TextStyle(fontSize: 30.0),),
                Text(" I am Jack "),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
