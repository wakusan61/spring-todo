package wakusan61.spring.todo.model;

public class Todo {
    private Integer id;

    private String title;

    private Status status;

    public enum Status implements CodeEnum {

        READY(0),IN_PROGRESS(1), COMPLETED(2);

        private final int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = Status.values()[status];
    }
}