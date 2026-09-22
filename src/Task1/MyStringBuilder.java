package Task1;

import java.util.ArrayDeque;
import java.util.Deque;

    public class MyStringBuilder {
    private final StringBuilder delegate = new StringBuilder();

    private final Deque<StateSnapshot> history = new ArrayDeque<>();

    private record StateSnapshot(int length) {}

    private void saveSnapshot() {
        history.push(new StateSnapshot(delegate.length()));
    }

    public MyStringBuilder append(String str) {
        saveSnapshot();
        delegate.append(str);
        return this;
    }

    public MyStringBuilder append(char c) {
        saveSnapshot();
        delegate.append(c);
        return this;
    }

    public void undo() {
        if (!history.isEmpty()) {
            StateSnapshot snapshot = history.pop();
            delegate.setLength(snapshot.length());
        }
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}