import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_lrn/shijian/othwe_todo/echo_widget.dart';
import 'package:flutter_lrn/shijian/induction_all.dart';
import 'package:flutter_lrn/shijian/normal_test_home_page_wdiget.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_custom_scroll_view.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_nested_scroll_tap.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_nested_scroll_view_2.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_scroller.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_tab_bar.dart';
import 'package:flutter_lrn/shijian/othwe_todo/test_will_scope.dart';
import 'package:flutter_localizations/flutter_localizations.dart';


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
      localizationsDelegates: const [
        // 本地化的代理类
        //为Material 组件库提供的本地化的字符串和其他值，
        // 它可以使Material 组件支持多语言。
        GlobalMaterialLocalizations.delegate,
        //定义组件默认的文本方向，从左到右或从右到左，
        // 这是因为有些语言的阅读习惯并不是从左到右，比如如阿拉伯语就是从右向左的
        GlobalWidgetsLocalizations.delegate,
      ],
      //表示我们的应用支持的语言列表，
        // 在本例中我们的应用只支持美国英语和中文简体两种语言。
      supportedLocales: const [
        Locale('en', 'US'), // 美国英语
        Locale('zh', 'CN'), // 中文简体
      ],
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      // home: const MyHomePage(title: 'TaoWei')
      // home: const NormalTestHomePage()
      // home: TestTabView()
      // home: TestCustomScrollViewWidget()
      // home: SnapAppBar()
      // home: NestedScrollTabViewWidget()
      home: InductionAllWidget()
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
