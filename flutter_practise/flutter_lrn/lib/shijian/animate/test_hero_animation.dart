import 'package:flutter/material.dart';
import 'package:flutter_lrn/template/template_widget.dart';

String HERO_TAG = "avatar";

class HeroAnimationWidget extends TemplateRoute {
  HeroAnimationWidget(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Container(
      alignment: Alignment.topCenter,
      child: Column(
        children: [
          InkWell(
            child: Hero(
              tag: HERO_TAG,
              child: ClipOval(
                child: Image.asset(
                  "pics/avatar.png",
                  width: 50.0,
                ),
              ),
            ),
            onTap: () {
              Navigator.push(context, PageRouteBuilder(pageBuilder:
                  (BuildContext context, animation, secondaryAnimation) {
                return FadeTransition(
                  opacity: animation,
                  child: NextPageRoute("下个页面"),
                );
              }));
            },
          ),
          Padding(
            padding: EdgeInsets.only(top: 8.0),
            child: Text("点击头像"),
          )
        ],
      ),
    );
  }
}

class NextPageRoute extends TemplateRoute {
  NextPageRoute(String name) : super(title: name);

  @override
  Widget getBody(BuildContext context) {
    return Center(
        child: Hero(
      tag: HERO_TAG,
      child: Image.asset(
        "pics/avatar.png",
      ),
    ));
  }
}
