package com.sistema.blog.dto;

import java.util.List;

public class PostResponsy {
    private List<PostDto> content;
    private int numPage;
    private int sizePage;
    private long totalElement;
    private int totalPage;
    private boolean ultima;

    public PostResponsy() {
    }

    public PostResponsy(List<PostDto> content, int numPage, int sizePage, long totalElement, int totalPage, boolean ultima) {
        this.content = content;
        this.numPage = numPage;
        this.sizePage = sizePage;
        this.totalElement = totalElement;
        this.totalPage = totalPage;
        this.ultima = ultima;
    }

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    public int getSizePage() {
        return sizePage;
    }

    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }
}
