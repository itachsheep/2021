import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/echo_widget.dart';
import 'package:flutter_lrn/shijian/normal_test_home_page_wdiget.dart';
import 'package:flutter_lrn/shijian/test_custom_scroll_view.dart';
import 'package:flutter_lrn/shijian/test_nested_scroll_view_2.dart';
import 'package:flutter_lrn/shijian/test_scroller.dart';
import 'package:flutter_lrn/shijian/test_tab_bar.dart';


void calculate(int a, int b) {

}

void collectLog(String line) {

}

void main() {
  runApp(const MyApp());
  /*runZoned(() => runApp(const MyApp()),
    zoneSpecification: ZoneSpecification(
      print:(Zone self, ZoneDelegate parent, Zone zone, String line) {
        collectLog(line);
        parent.print(zone, "Interceptor: $line");
      },

      handleUncaughtError: (Zone self, ZoneDelegate parent, Zone zone,
        Object error, StackTrace stackTrace) {
        parent.print(zone, '${error.toString()} $stackTrace');
      },
    )
  );*/

}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      // home: const MyHomePage(title: 'TaoWei')
      // home: const NormalTestHomePage()
      // home: TestTabView()
      // home: TestCustomScrollViewWidget()
      home: SnapAppBar()
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
            const Echo(
              text: "斯科拉里怕我怕我诶",
              backgroundColor: Color.fromARGB(255, 234, 190, 180)
            ),
            Builder(builder: (context) {
              Scaffold? scaffold = context.findAncestorWidgetOfExactType<Scaffold>();
              return (scaffold?.appBar as AppBar).title!;
            }),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
