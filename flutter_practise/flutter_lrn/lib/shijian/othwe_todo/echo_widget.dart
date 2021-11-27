

import 'package:flutter/material.dart';

class Echo extends StatelessWidget {
  final String text;
  final Color backgroundColor;
  const Echo({Key? key,
    required this.text,
    required this.backgroundColor,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        color:backgroundColor,
        child: Text(text),
      ),
    );
  }

}