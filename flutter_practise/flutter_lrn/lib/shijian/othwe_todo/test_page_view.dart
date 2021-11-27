
import 'package:flutter/cupertino.dart';
import 'package:flutter_lrn/log.dart';

class TestPageView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestPageViewState();
}
const tag = "TestPage.";

class TestPageViewState extends State<TestPageView>  {

  @override
  Widget build(BuildContext context) {
    var pages = <Widget>[];
    for(int i = 0; i < 6; i++) {
      pages.add(Page(text: "this is page: ${i + 1}"));
    }
    return PageView(
      children: pages,
      // allowImplicitScrolling: true,
    );
  }
}

class Page extends StatefulWidget {
  final String text;
  const Page({Key? key,required this.text}):super(key: key);

  @override
  State<StatefulWidget> createState() => PageState();
}
class PageState extends State<Page> with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    LogUtils.d(tag," PageState build ${widget.text}");
    return Center(
      child: Text(widget.text),
    );
  }

  @override
  bool get wantKeepAlive => true;// 是否需要缓存


}