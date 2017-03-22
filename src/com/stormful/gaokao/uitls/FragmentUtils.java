package com.stormful.gaokao.uitls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtils {
	private FragmentUtils() {
	}

	public static Fragment switchFragment(FragmentManager fragmentManager,
			int container, Fragment currentFragment,
			Class<? extends Fragment> newClazz, boolean addToBackStack) {
		return switchFragment(fragmentManager, container, currentFragment,
				newClazz, null, addToBackStack);
	}

	public static Fragment switchFragment(FragmentManager fragmentManager,
			int container, Fragment currentFragment,
			Class<? extends Fragment> newClazz, Bundle args,
			boolean addToBackStack) {
		try {
			fragmentManager.popBackStackImmediate(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
		} catch (Exception E) {
		}

		final FragmentTransaction transaction = fragmentManager
				.beginTransaction();
		final String tag = newClazz.getSimpleName();
		Fragment newFragment = fragmentManager.findFragmentByTag(tag);

		if (newFragment != null) {
			if (newFragment != currentFragment) {
				if (currentFragment != null) {
					transaction.hide(currentFragment);
				}
				transaction.show(newFragment);
				if (addToBackStack) {
					transaction.addToBackStack(null);
				}
				try {
					transaction.commit();
				} catch (Exception e) {
					transaction.commitAllowingStateLoss();
				}
			} else {
				if (newFragment.getArguments() != null)
					newFragment.getArguments().putAll(args);
			}
			return newFragment;
		} else {
			try {
				newFragment = newClazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		if (newFragment != null) {
			if (args != null && !args.isEmpty()) {
				final Bundle bundle = newFragment.getArguments();
				if (bundle != null) {
					bundle.putAll(args);
				} else {
					newFragment.setArguments(args);
				}
			}
		}

		if (currentFragment != null) {
			transaction.hide(currentFragment);
		}
		transaction.add(container, newFragment, tag);
		if (addToBackStack) {
			transaction.addToBackStack(null);
		}
		try {
			transaction.commit();
		} catch (Exception e) {
			transaction.commitAllowingStateLoss();
		}
		return newFragment;
	}
}
