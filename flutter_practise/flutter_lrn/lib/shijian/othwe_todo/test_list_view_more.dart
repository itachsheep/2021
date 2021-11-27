
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_lrn/log.dart';

class TestLoadMoreListView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => TestLoadMoreState();

}

class TestLoadMoreState extends State<TestLoadMoreListView> {
  static const tag = "TestLoadMoreState";
  static const loadingTag = "###loading###";
  var _words = <String>[loadingTag];

  @override
  void initState() {
    super.initState();
    LogUtils.d(tag,"initState");
    _loadMore(true);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("loadMore"),
      ),
      body: ListView.separated(
        itemBuilder: (ctx,index) {
          if(_words[index] == loadingTag) {
            if(_words.length < 50) {
              _loadMore(false);
              return loadMoreView();
            } else {
              return loadEndView();
            }
          }
          return ListTile(title: Text("${_words[index]}"));

        },
        separatorBuilder: (ctx,index) {
          return Divider(height: .0,);
        },
        itemCount: _words.length,
      ),
    );
  }

  Widget loadEndView() {
    return Container(
      alignment: Alignment.center,
      padding: EdgeInsets.all(16.0),
      child: Text(
        "没有更多了",
        style: TextStyle(color: Colors.grey),
      ),
    );
  }

  Widget loadMoreView() {
    return Container(
      padding: const EdgeInsets.all(16.0),
      alignment: Alignment.center,
      child: SizedBox(
        width: 24.0,
        height: 24.0,
        child: CircularProgressIndicator(strokeWidth: 2.0),
      ),
    );
  }

  void _loadMore(bool first) {
    LogUtils.d(tag, "loadmore");
    Future.delayed(Duration(seconds: 2)).then((value) => {
      setState((){
        _words.insertAll(_words.length -1, _generateWordPairs(first)
            .map((e){
              LogUtils.d(tag,"_loadMore: " + e);
              return e;
        }));
      })
    });
  }
  var loadMoreTime = 1;
  List<String> _generateWordPairs(bool first) {
    LogUtils.d(tag,"_generateWordPairs");
    int total = first ? 20 : 10;
    // List<String> _loadMore = List.empty();
    List<String> _loadMore = <String>[];

    for(int i = 0; i < total; i++) {
      // _loadMore[i] = "第$loadMoreTime次加载 $i";
      if(first) {
        _loadMore.insert(i,"this is item: $i");
      } else {
        _loadMore.insert(i,"第$loadMoreTime次加载 $i");
      }

    }
    if(!first) {
      loadMoreTime++;
    }
    return _loadMore;
  }
}