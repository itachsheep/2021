
import 'package:flutter/cupertino.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';

class TestStreamBuilder extends TemplateRoute {
  TestStreamBuilder(String name) : super(title: name);


  @override
  Widget getBody(BuildContext context) {
    return StreamBuilder<String>(
      stream: counterStream(), //
      //initialData: ,// a Stream<int> or null
      builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
        if (snapshot.hasError)
          return Text('Error: ${snapshot.error}');
        switch (snapshot.connectionState) {
          case ConnectionState.none:
            return Text('没有Stream');
          case ConnectionState.waiting:
            return Text('等待数据...');
          case ConnectionState.active:
            return Text('active: ${snapshot.data}');
          case ConnectionState.done:
            return Text('Stream 已关闭');
        }
        // return null; // unreachable
      },
    );
  }

  Stream<String> counterStream() {
    LogUtils.dd("counterStream");
    var stream = Stream.periodic(Duration(seconds: 2),(i){
      // if(i == 10) {
      //   stream.
      // }
      LogUtils.dd("i : $i");
      return i.toString();
    });

    return stream;
  }



}
