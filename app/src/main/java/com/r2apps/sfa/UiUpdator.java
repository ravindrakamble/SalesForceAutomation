package com.r2apps.sfa;

import com.r2apps.sfa.http.RestResponse;

public interface UiUpdator {

	void updateUI(int requestCode, RestResponse.StatusCode responseCode, Object data);
}
