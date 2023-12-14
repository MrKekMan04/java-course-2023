package edu.hw11.task3;

import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import org.objectweb.asm.Opcodes;

public class FibonacciImpl implements Implementation {
    @Override
    public ByteCodeAppender appender(Target target) {
        final String owner = "FibCalculator";
        final String methodName = "fib";
        final String methodDescriptor = "(I)J";
        final int maxMethodOperandStackSize = 4;

        return (methodVisitor, context, methodDescription) -> {
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            final Label ifStatement = new Label();
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, ifStatement);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitLabel(ifStatement);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, owner, methodName, methodDescriptor, false);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, owner, methodName, methodDescriptor, false);
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            return new ByteCodeAppender.Size(maxMethodOperandStackSize, 1);
        };
    }

    @Override
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }
}
