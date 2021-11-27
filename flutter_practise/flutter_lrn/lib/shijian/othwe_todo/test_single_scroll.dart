import 'package:flutter/material.dart';

class TestSingleScrollWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestSingleScrollState();
}

class TestSingleScrollState extends State<TestSingleScrollWidget> {
  @override
  Widget build(BuildContext context) {
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return Scaffold(
      appBar: AppBar(
        title: Text("singleScroll"),
      ),
      body: Scrollbar(
        child: SingleChildScrollView(
          padding: EdgeInsets.all(16.0),
          child: Center(
            child: Column(
              children: str
                  .split("")
                  .map((c) => Text(
                        c,
                        textScaleFactor: 2.0,
                      ))
                  .toList(),
            ),
          ),
        ),
      ),
    );
  }
}
