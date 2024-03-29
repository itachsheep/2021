

import 'package:flutter/material.dart';

abstract class TemplateRoute extends StatelessWidget {
  final String title;
  TemplateRoute({Key? key,required this.title}) : super(key: key);

  // Widget getTitle();
  Widget getBody(BuildContext context);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(this.title),
      ),
      body: getBody(context),
    );
  }
}


