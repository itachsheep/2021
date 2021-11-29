

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
      //LogUtils.dd("msg")
      return 0;
    }
    
  }

}
