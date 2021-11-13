

import 'package:flutter/material.dart';

abstract class TemplateRoute extends StatelessWidget {
  const TemplateRoute({Key? key}) : super(key: key);

  Widget getTitle();
  Widget getBody();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: getTitle(),
      ),
      body: getBody(),
    );
  }
}


