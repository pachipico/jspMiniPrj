package domain;

public class CommentVO {

	private long cmtId;
	private String email;
	private String regAt;
	private String modAt;
	private long postId;
	private String content;

	public CommentVO() {
	}

	// register
	public CommentVO(String email, long postId, String content) {
		this.email = email;
		this.postId = postId;
		this.content = content;
	}

	// list
	public CommentVO(String email, String modAt, String content) {
		this.email = email;
		this.modAt = modAt;
		this.content = content;
	}

	// update
	public CommentVO(long postId, String content) {
		this.postId = postId;
		this.content = content;
	}

	public long getCmtId() {
		return cmtId;
	}

	public void setCmtId(long cmtId) {
		this.cmtId = cmtId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegAt() {
		return regAt;
	}

	public void setRegAt(String regAt) {
		this.regAt = regAt;
	}

	public String getModAt() {
		return modAt;
	}

	public void setModAt(String modAt) {
		this.modAt = modAt;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentVO [cmtId=" + cmtId + ", email=" + email + ", regAt=" + regAt + ", modAt=" + modAt + ", postId="
				+ postId + ", content=" + content + "]";
	}

}
