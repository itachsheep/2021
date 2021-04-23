/**
 * @ClassName:
 * @Description:
 * @author taowei
 * @version V1.0
 * @Date
 */

package com.tao.opensourcecode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GitHubApiService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user")String user);
}
