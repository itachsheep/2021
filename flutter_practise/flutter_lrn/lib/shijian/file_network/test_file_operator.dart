

import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';
import 'package:flutter_lrn/template/template_widget.dart';
import 'package:path_provider/path_provider.dart';

class FileOperationTestRoute extends TemplateRoute {
  FileOperationTestRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return FileOperationRoute();
  }

}

class FileOperationRoute extends StatefulWidget {
  FileOperationRoute({Key? key}) : super(key: key);

  @override
  _FileOperationRouteState createState() => _FileOperationRouteState();
}

class _FileOperationRouteState extends State<FileOperationRoute> {
  int _counter = 0;

  @override
  void initState() {
    super.initState();
    _readCount().then((value){
      setState(() {
        _counter = value;
      });
    });
  }

  Future<File> _getLocalFile() async {
    String dir = (await getApplicationDocumentsDirectory()).path;
    return File("$dir/counter.txt");
  }

  Future<int> _readCount() async {
    try {
      File file = await _getLocalFile();
      String content = await file.readAsString();
      return int.parse(content);
    } on FileSystemException {
      LogUtils.dd("FileSystemException ");
      return 0;
    }
  }

  _incrementCounter() async {
    setState(() {
      _counter++;
    });
    // 将点击次数以字符串类型写到文件中
    await (await _getLocalFile()).writeAsString('$_counter');
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child:Column(
        children: [
          Text("点击了 $_counter 次"),
          Padding(padding: EdgeInsets.only(top: 10)),
          ElevatedButton(
              onPressed: _incrementCounter,
              child: Text("inCrement"))
        ],
      ),

    );
  }

}
